package com.chenxulu.myapps.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.chenxulu.myapps.R;
import com.chenxulu.myapps.draw.util.TextDraw;
import com.chenxulu.myapps.draw.util.TextObject;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by xulu on 2017/1/22.
 */
public class MyDrawView extends PhotoView implements View.OnTouchListener, PhotoViewAttacher.OnMatrixChangedListener {
    private Canvas cacheCanvas;
    private Bitmap cacheBitmap;
    private Paint mPaint;

    private List<MyPath> mList;

    private RectF mRectF;
    private float mScale = 1.0f;
    private List<PointF> tempList;
    private Path tempPath;

    private boolean mDrawEnable;

    private TextObject textObject;
    private TextDraw textDraw;

    public MyDrawView(Context context) {
        this(context, null, 0);
    }

    public MyDrawView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        tempPath = new Path();

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);

        cacheCanvas = new Canvas();
        initCanvas();

        mList = new ArrayList<>();

        mRectF = new RectF(0, 0, 0, 0);

        setMyTouchListener(this);
        setOnMatrixChangeListener(this);

        setCanDraw(false);
    }

    private void initCanvas() {
        int view_width = getResources().getDisplayMetrics().widthPixels;
        int view_height = getResources().getDisplayMetrics().heightPixels;
        cacheBitmap = Bitmap.createBitmap(view_width, view_height, Bitmap.Config.ARGB_8888);// 建立图像缓冲区用来保存图像
        cacheCanvas.setBitmap(cacheBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(cacheBitmap, 0, 0, mPaint);
        if (textObject != null) {
            textObject.draw(canvas, mScale, mRectF);
        }
    }


    @Override
    public void onMatrixChanged(RectF rect) {
        float scale = (rect.right - rect.left) / getWidth();
        boolean scaleChanged = (scale != mScale);
        boolean rectChanged = !rect.toString().equals(mRectF.toString());

        mScale = scale;
        mRectF = new RectF(rect);
        if (scaleChanged || (!mDrawEnable && rectChanged)) {
            MyDrawUtil.drawPathList(this, cacheCanvas, mList, rect);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (textObject != null && textObject.contains(event.getX(), event.getY())) {
            if (event.getPointerCount() == 1) {
                textDraw.singleTouchEvent(event);
            } else {
                textDraw.multiTouchEvent(event);
            }
            invalidate();
            return true;
        }

        if (mDrawEnable && event.getPointerCount() == 1) {
            float x = (event.getX() - mRectF.left) / mScale;
            float y = (event.getY() - mRectF.top) / mScale;

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    tempList = new ArrayList<>();

                    tempPath.moveTo(event.getX(), event.getY());
                    break;
                case MotionEvent.ACTION_MOVE:
                    PointF pointF = new PointF(x, y);
                    tempList.add(pointF);

                    tempPath.lineTo(event.getX(), event.getY());
                    cacheCanvas.drawPath(tempPath, mPaint);
                    break;
                case MotionEvent.ACTION_UP:
                    if (tempList.size() > 1) {
                        MyPath myPath = new MyPath(tempList, new Paint(mPaint));
                        mList.add(myPath);
                    }
                    tempPath.reset();
                    break;
                default:
                    break;
            }
            invalidate();
        }
        return false;
    }

    /**
     * 设置画笔的颜色
     *
     * @param color
     */
    public void setPaintColor(int color) {
        mPaint.setColor(color);
    }

    /**
     * 撤销
     */
    public void undo() {
        if (mList.size() > 0) {
            mList.remove(mList.size() - 1);
            MyDrawUtil.drawPathList(this, cacheCanvas, mList, mRectF);
        }
    }

    /**
     * 是否可以涂鸦
     *
     * @param canDraw
     */
    public void setCanDraw(boolean canDraw) {
        this.mDrawEnable = canDraw;
        setCanDrag(!canDraw);
    }

    public void addText(String text) {
        Bitmap rotate = BitmapFactory.decodeResource(getResources(), R.drawable.draw_txt_rotate);
        Bitmap delete = BitmapFactory.decodeResource(getResources(), R.drawable.draw_txt_delete);

        textObject = new TextObject(text, getWidth() / 2, getHeight() / 2, rotate, delete);

        textDraw = new TextDraw(textObject, mRectF);

        invalidate();
    }
}
