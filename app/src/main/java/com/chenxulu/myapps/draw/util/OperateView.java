package com.chenxulu.myapps.draw.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jarlen
 */
public class OperateView extends View {
    private List<ImageObject> imgLists = new ArrayList<>();

    private Rect mCanvasLimits;
    private Bitmap bgBitmap;
    private Paint paint = new Paint();
    private boolean isMultiAdd;// true 代表可以添加多个水印图片（或文字），false 代表只可添加单个水印图片（或文字）
    private float picScale = 0.4f;

    private TextDraw textDraw;

    public OperateView(Context context, Bitmap resizeBmp) {
        super(context);
        bgBitmap = resizeBmp;
        int width = bgBitmap.getWidth();
        int height = bgBitmap.getHeight();

        mCanvasLimits = new Rect(0, 0, width, height);

    }

    /**
     * 设置水印图片初始化大小
     *
     * @param picScale
     */
    public void setPicScale(float picScale) {
        this.picScale = picScale;
    }

    /**
     * 设置是否可以添加多个图片或者文字对象
     *
     * @param isMultiAdd true 代表可以添加多个水印图片（或文字），false 代表只可添加单个水印图片（或文字）
     */
    public void setMultiAdd(boolean isMultiAdd) {
        this.isMultiAdd = isMultiAdd;
    }

    /**
     * 将图片对象添加到View中
     *
     * @param imgObj 图片对象
     */
    public void addItem(ImageObject imgObj) {
        if (imgObj == null) {
            return;
        }
        if (!isMultiAdd && imgLists != null) {
            imgLists.clear();
        }
        if (!imgObj.isTextObject) {
            imgObj.setScale(picScale);
        }
        imgLists.add(imgObj);
        invalidate();
    }

    /**
     * 画出容器内所有的图像
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int sc = canvas.save();
        canvas.clipRect(mCanvasLimits);
        canvas.drawBitmap(bgBitmap, 0, 0, paint);
        drawImages(canvas);
        canvas.restoreToCount(sc);

        for (ImageObject imageObject : imgLists) {
            if (imageObject != null) {
                imageObject.drawIcon(canvas);
            }
        }
    }

    /**
     * 根据触控点重绘View
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getPointerCount() == 1) {
            textDraw.singleTouchEvent(event);
        } else {
            textDraw.multiTouchEvent(event);
        }
        invalidate();

        super.onTouchEvent(event);
        return true;
    }


    /**
     * 循环画图像
     *
     * @param canvas
     */
    private void drawImages(Canvas canvas) {
        for (ImageObject ad : imgLists) {
            if (ad != null) {
                ad.draw(canvas);
            }
        }
    }

    public void setOnListener(TextDraw.MyListener myListener) {
        textDraw.setOnListener(myListener);
    }

}
