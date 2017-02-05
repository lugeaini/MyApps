package com.chenxulu.myapps.draw.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author jarlen
 */
public class TextObject extends ImageObject {
    private int textSize = 90;
    private int color = Color.BLACK;
    private String text;

    Paint paint = new Paint();

    /**
     * 构造方法
     *
     * @param text     输入的文字
     * @param x        位置x坐标
     * @param y        位置y坐标
     * @param rotateBm 旋转按钮的图片
     * @param deleteBm 删除按钮的图片
     */
    public TextObject(String text, int x, int y, Bitmap rotateBm, Bitmap deleteBm) {
        super();
        this.text = text;
        this.mPoint.x = x;
        this.mPoint.y = y;
        this.rotateBm = rotateBm;
        this.deleteBm = deleteBm;

        regenerateBitmap();
    }

    /**
     * 绘画出字体
     */
    public void regenerateBitmap() {
        paint.setAntiAlias(true);
        paint.setTextSize(textSize);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setDither(true);
        paint.setFlags(Paint.SUBPIXEL_TEXT_FLAG);
        String lines[] = text.split("\n");

        int textWidth = 0;
        for (String str : lines) {
            int temp = (int) paint.measureText(str);
            if (temp > textWidth)
                textWidth = temp;
        }
        if (textWidth < 1)
            textWidth = 1;

        if (srcBitmap != null)
            srcBitmap.recycle();

        srcBitmap = Bitmap.createBitmap(textWidth, textSize * (lines.length) + 8, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(srcBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        for (int i = 1; i <= lines.length; i++) {
            canvas.drawText(lines[i - 1], 0, i * textSize, paint);
        }

        setCenter();
    }

    /**
     * 设置属性值后，提交方法
     */
    public void commit() {
        regenerateBitmap();
    }

    /**
     * 公共的getter和setter方法
     */
    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
