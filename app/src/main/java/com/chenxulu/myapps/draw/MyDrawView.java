package com.chenxulu.myapps.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

import static android.R.attr.x;

/**
 * Created by xulu on 2017/1/22.
 */
public class MyDrawView extends PhotoView implements View.OnTouchListener, PhotoViewAttacher.OnMatrixChangedListener {
    private Paint mPaint;
    private List<PointF> list;
    private RectF mRectF;

    public MyDrawView(Context context) {
        this(context, null, 0);
    }

    public MyDrawView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        list = new ArrayList<>();

        setMyTouchListener(this);
        setOnMatrixChangeListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 1; i < list.size(); i++) {
            PointF last1 = list.get(i - 1);
            PointF last2 = list.get(i);
            if (mRectF != null)
                canvas.drawLine(last1.x + mRectF.left, last1.y + mRectF.top, last2.x + mRectF.left, last2.y + mRectF.top, mPaint);
            else
                canvas.drawLine(last1.x, last1.y, last2.x, last2.y, mPaint);
        }
    }

    @Override
    public void onMatrixChanged(RectF rect) {
        System.out.println(rect.toString());
        mRectF = rect;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getPointerCount() == 1) {
            float x = event.getX();
            float y = event.getY();
            PointF pointF = new PointF(x, y);
            list.add(pointF);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    break;
            }
            postInvalidate();
        }
        return false;
    }

}
