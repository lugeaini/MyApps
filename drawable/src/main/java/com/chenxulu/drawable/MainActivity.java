package com.chenxulu.drawable;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    ImageView logoView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoView = (ImageView) findViewById(R.id.image_view);
        textView = (TextView) findViewById(R.id.text_view);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDisplayMetrics();
            }
        });

    }

    void showDisplayMetrics() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        StringBuilder builder = new StringBuilder();
        //builder.append("xdpi:" + metrics.xdpi + ",ydpi:" + metrics.ydpi);
        builder.append("\ndensityDpi:" + metrics.densityDpi);
        builder.append("\ndensity:" + metrics.density);
        //builder.append("\nscaledDensity:" + metrics.scaledDensity);
        builder.append("\nwidthPixels:" + metrics.widthPixels);
        builder.append(",heightPixels:" + metrics.heightPixels);
        builder.append("\n\nwidth:" + logoView.getWidth() + ",height:" + logoView.getHeight());
        textView.setText(builder.toString());
    }
}
