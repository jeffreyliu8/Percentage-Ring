package javis.customview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Random;

import jliu.percentagering.PercentRingView;

public class DefaultActivity extends AppCompatActivity {
    PercentRingView circle1;
    PercentRingView circle2;
    PercentRingView circle3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                int i1 = r.nextInt(60) + 1;
                int i2 = r.nextInt(60);
                circle1.setPercentageAndAnimate(i2, i1, "of " + i1 + " days");

                i1 = r.nextInt(60) + 1;
                i2 = r.nextInt(60);
                circle2.setPercentageAndAnimate(i2, i1, "of " + i1 + " min");

                i1 = r.nextInt(60) + 1;
                i2 = r.nextInt(60);
                circle3.setPercentageAndAnimate(i2, i1, "of " + i1 + " days");
            }
        });

        circle1 = (PercentRingView) findViewById(R.id.circle1);
        circle2 = (PercentRingView) findViewById(R.id.circle2);
        circle3 = (PercentRingView) findViewById(R.id.circle3);

        circle1.setPercentageAndAnimate(2, 4, "of 4 days");
        circle2.setPercentageAndAnimate(8, 30, "of 30 min");
        circle3.setPercentageAndAnimate(15, 16, "of 16 days");
    }
}