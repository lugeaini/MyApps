package com.chenxulu.myapps;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author xulu
 */
public class DeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        Button getBtn = (Button) findViewById(R.id.main_btn);
        final EditText widthPxText = (EditText) findViewById(R.id.main_et_width_px);
        final EditText heightPxText = (EditText) findViewById(R.id.main_et_height_px);
        final EditText densityText = (EditText) findViewById(R.id.main_et_density);
        final EditText densityDpiText = (EditText) findViewById(R.id.main_et_density_dpi);
        final EditText widthDipText = (EditText) findViewById(R.id.main_et_width_dip);
        final EditText heightDipText = (EditText) findViewById(R.id.main_et_height_dip);
        getBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 屏幕宽度(px)
                int widthPx = getResources().getDisplayMetrics().widthPixels;
                // 屏幕高度(px)
                int heightPx = getResources().getDisplayMetrics().heightPixels;
                widthPxText.setText(widthPx + "");
                heightPxText.setText(heightPx + "");
                // 屏幕密度:指每平方英寸中的像素数,在DisplayMetrics类中，该密度值为dpi/160
                float density = getResources().getDisplayMetrics().density;
                // 屏幕密度(dpi):指每英寸中的像素数
                float densityDpi = getResources().getDisplayMetrics().densityDpi;
                densityText.setText(density + "");
                densityDpiText.setText(densityDpi + "");
                // 屏幕宽度(dip)
                int widthDip = pxToDip(getApplication(), widthPx);
                // 屏幕高度(dip)
                int heightDip = pxToDip(getApplication(), heightPx);
                widthDipText.setText(widthDip + "");
                heightDipText.setText(heightDip + "");
            }
        });

    }

    /**
     * px值向dip值转换
     *
     * @param context
     * @param pxValue
     * @return
     */
    private int pxToDip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * dip值向px值转换
     *
     * @param context
     * @param dipValue
     * @return
     */
    public int dipToPx(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
