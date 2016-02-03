package javis.customview;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class DefaultActivity extends AppCompatActivity {
    PercentView circle1;
    PercentView circle2;
    PercentView circle3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        circle1 = (PercentView) findViewById(R.id.circle1);
        circle1.setPercentage(2, 4, "of 4 days");

        circle2 = (PercentView) findViewById(R.id.circle2);
        circle2.setPercentage(29, 30, "of 30 min");
        circle3 = (PercentView) findViewById(R.id.circle3);
        circle3.setPercentage(15, 16, "of 16 days");
    }

    @Override
    protected void onResume() {
        super.onResume();
        animatePercentageView(circle1);
        animatePercentageView(circle2);
        animatePercentageView(circle3);
    }

    public void animatePercentageView(final PercentView percentView) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, percentView.getNumerator());
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float number = Float.parseFloat(valueAnimator.getAnimatedValue().toString());
                percentView.setAnimatedNumerator(number);
            }
        });
        valueAnimator.start();
    }
}