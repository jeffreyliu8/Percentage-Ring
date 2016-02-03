package javis.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by jeffliu on 1/20/16.
 */
public class PercentView extends View {
    private static final String TAG = "PercentView";

    public PercentView(Context context) {
        super(context);
        init();
    }

    public PercentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

//        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PercentView, 0, 0);
//        try {
//            color = ta.getColor(R.styleable.PercentView, 100.0f);
//        } finally {
//            ta.recycle();
//        }

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PercentView,
                0, 0);

        try {
            ringColor = a.getColor(R.styleable.PercentView_ringColor, Color.BLACK);
            numerator = a.getInt(R.styleable.PercentView_numerator, 0);
            denominator = a.getInt(R.styleable.PercentView_denominator, 1);
            lowerText = a.getString(R.styleable.PercentView_lowerText);
            upperTextSize = a.getInt(R.styleable.PercentView_upperTextSize, 100);
            lowerTextSize = a.getInt(R.styleable.PercentView_lowerTextSize, 32);
            upperYOffset = a.getInt(R.styleable.PercentView_upperYOffset, 20);
            lowerYOffset = a.getInt(R.styleable.PercentView_lowerYOffset, 50);
        } finally {
            a.recycle();
        }
    }

    public PercentView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        rect = new RectF();

        upperTextPaint.setColor(Color.WHITE);
        upperTextPaint.setTextAlign(Paint.Align.CENTER);

        lowerTextPaint.setColor(Color.WHITE);
        lowerTextPaint.setTextAlign(Paint.Align.CENTER);
        lowerTextPaint.setAlpha(ALPHA);
    }


    private RectF rect;
    private static final float strokeWidth = 16;
    private int ringColor = Color.BLACK;
    private int numerator = 0;
    private float animatedNumerator = -1;
    private int denominator = 1;
    private String lowerText = "";
    private int upperTextSize = 100;
    private int lowerTextSize = 50;
    private int upperYOffset = 20;
    private int lowerYOffset = 50;
    private static final int ALPHA = 153; // 255
    private static final int ALPHA_RING = 51; // 255


    private Paint upperTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint lowerTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint mArcPaint = new Paint() {
        {
            setDither(true);
            setStyle(Paint.Style.STROKE);
            setStrokeCap(Paint.Cap.ROUND);
            setStrokeJoin(Paint.Join.ROUND);
            setColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_bright));
            setAlpha(ALPHA_RING);
            setStrokeWidth(strokeWidth);
            setAntiAlias(true);
        }
    };

    private final Paint mArcPaintFill = new Paint() {
        {
            setDither(true);
            setStyle(Paint.Style.STROKE);
            setStrokeCap(Paint.Cap.ROUND);
            setStrokeJoin(Paint.Join.ROUND);
            setColor(ContextCompat.getColor(getContext(), android.R.color.black));
            setStrokeWidth(strokeWidth);
            setAntiAlias(true);
        }
    };

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw background circle anyway
        int left = 0;
        int width = getWidth();
        int top = 0;

        float strockWidth = strokeWidth;

        rect.set(left + strockWidth, top + strockWidth, left + width - strockWidth, top + width - strockWidth);
        mArcPaint.setColor(ringColor);
        mArcPaint.setAlpha(ALPHA_RING);
        mArcPaintFill.setColor(ringColor);
        canvas.drawArc(rect, 0, 360, false, mArcPaint);

        float sweepAngle;
        if (animatedNumerator != -1) {
            sweepAngle = animatedNumerator / (float) denominator * 360;
//            if (percentage == ((float) numerator / (float) denominator)) {
//                percentage = -1;
//            }
        } else {
            sweepAngle = (float) numerator / (float) denominator * 360;
        }
        canvas.drawArc(rect, -89, sweepAngle, false, mArcPaintFill); // 89 looks better than 90..

        int xTopTextPos = canvas.getWidth() / 2;
        int yTopTextPos = (int) (((top + width) / 2) - ((upperTextPaint.descent() + upperTextPaint.ascent()) / 2)) - upperYOffset;
        //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.
        upperTextPaint.setTextSize(upperTextSize);
        canvas.drawText(Integer.toString(numerator != (int) animatedNumerator && animatedNumerator >= 0 ? (int) animatedNumerator : numerator), xTopTextPos, yTopTextPos, upperTextPaint);

        int xBottomTextPos = canvas.getWidth() / 2;
        int yBotoomTextPos = (int) (((top + width) / 2) - ((lowerTextPaint.descent() + lowerTextPaint.ascent()) / 2)) + lowerYOffset;
        //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.
        lowerTextPaint.setTextSize(lowerTextSize);
        canvas.drawText(lowerText, xBottomTextPos, yBotoomTextPos, lowerTextPaint);
    }

    public void setPercentage(int numerator, int denominator, String lowerText) {
        if (numerator < 0 || denominator <= 0 || numerator > denominator) {
            Log.e(TAG, "setPercentage: invalid input");
            return;
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.lowerText = lowerText;
        invalidate();
    }

    // this for animation only
    public void setAnimatedNumerator(float displayAnimatedNumerator) {
        if (displayAnimatedNumerator < 0) {
            Log.e(TAG, "setAnimatedNumerator: invalid input");
            return;
        }
        this.animatedNumerator = displayAnimatedNumerator;
        invalidate();
    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }
}