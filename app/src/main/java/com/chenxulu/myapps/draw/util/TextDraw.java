package com.chenxulu.myapps.draw.util;

import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;

/**
 * Created by xulu on 2017/2/4.
 */

public class TextDraw {
    public static final double ROTATION_STEP = 2.0;

    private boolean mMoved;
    private boolean mResizeAndRotate;

    private float mStartDistance;
    private float mStartScale;

    private float mStartRotation;
    private float mStartDegrees;

    private PointF tempPoint = new PointF();

    private RectF mCanvasRect;
    private ImageObject mImageObject;

    private long selectTime = 0;

    public TextDraw(ImageObject imageObject, RectF mCanvasRect) {
        this.mImageObject = imageObject;
        this.mCanvasRect = mCanvasRect;
    }

    /**
     * 多点触控操作
     *
     * @param event
     */
    public void multiTouchEvent(MotionEvent event) {
        float offsetX = event.getX(1) - event.getX(0);
        float offsetY = event.getY(1) - event.getY(0);
        float distance = (float) Math.sqrt((offsetX * offsetX + offsetY * offsetY));
        float degrees = (float) Math.toDegrees(Math.atan2(offsetX, offsetY));

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_POINTER_DOWN:
                mStartDistance = distance;
                mStartDegrees = degrees;

                mStartScale = mImageObject.getScale();
                mStartRotation = mImageObject.getRotation();
                break;
            case MotionEvent.ACTION_MOVE:
                float newScale = (distance / mStartDistance) * mStartScale;
                float offsetDegrees = mStartDegrees - degrees;

                if (newScale < 10.0f && newScale > 0.1f) {
                    float newRotation = Math.round((mStartRotation + offsetDegrees) / 1.0f);
                    if (Math.abs((newScale - mImageObject.getScale()) * ROTATION_STEP) > Math.abs(newRotation - mImageObject.getRotation())) {
                        mImageObject.setScale(newScale);
                    } else {
                        mImageObject.setRotation(newRotation % 360);
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
        }
    }

    /**
     * 单点触控操作
     *
     * @param event
     */
    public void singleTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mMoved = false;
                mResizeAndRotate = false;

                boolean touchInside = mImageObject.contains(event.getX(), event.getY());
                boolean touchDelete = mImageObject.pointOnCorner(event.getX(), event.getY(), OperateConstants.LEFT_TOP);
                boolean touchRotate = mImageObject.pointOnCorner(event.getX(), event.getY(), OperateConstants.RIGHT_BOTTOM);

                if (touchInside || touchDelete || touchRotate) {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - selectTime < 300) {
                        if (myListener != null) {
                            if (mImageObject.isTextObject()) {
                                myListener.onClick((TextObject) mImageObject);
                            }
                        }
                    }
                    selectTime = currentTime;
                    break;
                }
                if (touchDelete) {
                    //delete
                } else if (touchRotate) {
                    mResizeAndRotate = true;
                    float offsetX = event.getX() - mImageObject.getPoint().x;
                    float offsetY = event.getY() - mImageObject.getPoint().y;

                    mStartDistance = (float) Math.sqrt((offsetX * offsetX + offsetY * offsetY));
                    mStartDegrees = (float) Math.toDegrees(Math.atan2(offsetX, offsetY));
                    mStartScale = mImageObject.getScale();
                    mStartRotation = mImageObject.getRotation();
                } else if (touchInside) {
                    mMoved = true;
                    tempPoint.set(event.getX(), event.getY());
                }
                break;
            case MotionEvent.ACTION_MOVE:
                // 移动
                if (mMoved) {
                    float offsetX = event.getX() - tempPoint.x;
                    float offsetY = event.getY() - tempPoint.y;

                    tempPoint.set(event.getX(), event.getY());

                    PointF point = mImageObject.getPoint();
                    if (mCanvasRect.contains(point.x + offsetX, point.y + offsetY)) {
                        point.offset(offsetX, offsetY);
                    }
                }
                // 旋转和缩放
                if (mResizeAndRotate) {
                    float offsetX = event.getX() - mImageObject.getPoint().x;
                    float offsetY = event.getY() - mImageObject.getPoint().y;

                    float distance = (float) Math.sqrt((offsetX * offsetX + offsetY * offsetY));
                    float newScale = (distance / mStartDistance) * mStartScale;

                    float newDegrees = (float) Math.toDegrees(Math.atan2(offsetX, offsetY));
                    float offsetDegrees = mStartDegrees - newDegrees;

                    if (newScale < 10.0f && newScale > 0.1f) {
                        float newRotation = Math.round((mStartRotation + offsetDegrees) / 1.0f);
                        if (Math.abs((newScale - mImageObject.getScale()) * ROTATION_STEP) > Math.abs(newRotation - mImageObject.getRotation())) {
                            mImageObject.setScale(newScale);
                        } else {
                            mImageObject.setRotation(newRotation % 360);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mMoved = false;
                mResizeAndRotate = false;
                break;
        }
        //cancelLongPress();
    }

    /**
     * 向外部提供双击监听事件（双击弹出自定义对话框编辑文字）
     */
    MyListener myListener;

    public void setOnListener(MyListener myListener) {
        this.myListener = myListener;
    }

    public interface MyListener {
        void onClick(TextObject tObject);
    }

}
