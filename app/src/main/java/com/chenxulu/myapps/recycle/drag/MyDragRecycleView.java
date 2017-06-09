package com.chenxulu.myapps.recycle.drag;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.chenxulu.myapps.R;
import com.chenxulu.myapps.recycle.base.MyRecycleView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xulu on 2016/12/29.
 */
public class MyDragRecycleView extends MyRecycleView {
    private ImageView mDragView;

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowLayoutParams;

    private int mDragPosition;
    private boolean isDrag = false;

    private int moveX;
    private int moveY;

    private boolean mAnimationEnd = true;

    private DragViewListener mListener;
    private int mDisplayHeight;
    private int statusHeight;

    public MyDragRecycleView(Context context) {
        this(context, null);
    }

    public MyDragRecycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyDragRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mDisplayHeight = getResources().getDisplayMetrics().heightPixels;
        statusHeight = getStatusHeight(getContext());
    }

    /**
     * 开始拖动
     */
    public void startDrag(View dragView, int position) {
        isDrag = true;
        // 震动一下
        Vibrator mVibrator = (Vibrator) getContext().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        mVibrator.vibrate(50);

        mDragPosition = position;
        if (mListener != null)
            mListener.setHideItem(mDragPosition);

        createDragView(dragView);
    }

    /**
     * 停止拖拽
     */
    private void onStopDrag() {
        isDrag = false;
        if (mListener != null)
            mListener.setHideItem(-1);
        if (mDragView != null) {
            mWindowManager.removeViewImmediate(mDragView);
            mDragView = null;
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //MotionEventUtil.log("onInterceptTouchEvent", ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveX = (int) ev.getX();
                moveY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDrag) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                onStopDrag();
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //MotionEventUtil.log("onTouchEvent", ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveX = (int) ev.getX();
                moveY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                if (isDrag) {
                    updateDragView(x - moveX, y - moveY);
                    onSwapItem(x, y);
                }
                moveX = x;
                moveY = y;
                break;
            case MotionEvent.ACTION_UP:
                if (isDrag)
                    onStopDrag();
                break;
        }
        if (isDrag)
            return true;
        return super.onTouchEvent(ev);
    }

    /**
     * 创建拖动的镜像
     */
    private void createDragView(View view) {
        view.setDrawingCacheEnabled(true);// 开启mDragItemView绘图缓存
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());// 获取mDragItemView在缓存中的Bitmap对象
        view.destroyDrawingCache();// 这一步很关键，释放绘图缓存，避免出现重复的镜像

        int location[] = new int[2];
        view.getLocationOnScreen(location);

        mWindowLayoutParams = new WindowManager.LayoutParams();
        mWindowLayoutParams.format = PixelFormat.TRANSLUCENT; // 图片之外的其他地方透明
        mWindowLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        mWindowLayoutParams.x = location[0];
        mWindowLayoutParams.y = location[1] - statusHeight;
        mWindowLayoutParams.alpha = 0.55f; // 透明度
        mWindowLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        mDragView = new ImageView(getContext());
        mDragView.setImageBitmap(bitmap);
        mDragView.setBackgroundResource(R.color.dark_slate_gray);
        mWindowManager.addView(mDragView, mWindowLayoutParams);
    }

    /**
     * 更新DragView位置
     */
    private void updateDragView(int offsetX, int offsetY) {
        mWindowLayoutParams.x += offsetX;
        mWindowLayoutParams.y += offsetY;

        if (mWindowLayoutParams.y < getTop()) {
            smoothScrollBy(0, -30);
            mWindowLayoutParams.y += 20;
        } else if (mWindowLayoutParams.y + mDragView.getHeight() + statusHeight > mDisplayHeight) {
            smoothScrollBy(0, 30);
            mWindowLayoutParams.y -= 20;
        }
        mWindowManager.updateViewLayout(mDragView, mWindowLayoutParams);
    }

    /**
     * 交换item,并且控制item之间的显示与隐藏效果
     */
    private void onSwapItem(int x, int y) {
        final int tempPosition = pointToPosition(x, y);
        if (tempPosition == NO_POSITION || tempPosition == mDragPosition)
            return;
        if (mListener != null && !mListener.canDrag(tempPosition))
            return;

        if (mAnimationEnd) {
            if (mListener != null) {
                mListener.reorderItems(mDragPosition, tempPosition);
                mListener.setHideItem(tempPosition);
            }

            final ViewTreeObserver observer = getViewTreeObserver();
            observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

                @Override
                public boolean onPreDraw() {
                    observer.removeOnPreDrawListener(this);
                    animateReorder(mDragPosition, tempPosition);
                    mDragPosition = tempPosition;
                    return true;
                }
            });

        }
    }

    /**
     * 创建移动动画
     */
    private AnimatorSet createAnimations(View view, float startX, float endX, float startY, float endY) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(view, "translationX", startX, endX);
        ObjectAnimator animY = ObjectAnimator.ofFloat(view, "translationY", startY, endY);
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(animX, animY);
        return animSetXY;
    }

    /**
     * item的交换动画效果
     */
    private void animateReorder(final int oldPosition, final int newPosition) {
        int numColumns = getGridViewSpanCount();

        boolean isForward = newPosition > oldPosition;
        List<Animator> resultList = new LinkedList<>();
        if (isForward) {
            for (int position = oldPosition; position < newPosition; position++) {
                View view = getChildAt(position - getFirstVisiblePosition());
                if ((position + 1) % numColumns == 0) {
                    float startX = -view.getWidth() * (numColumns - 1);
                    float startY = view.getHeight();
                    AnimatorSet animatorSet = createAnimations(view, startX, 0, startY, 0);
                    resultList.add(animatorSet);
                } else {
                    AnimatorSet animatorSet = createAnimations(view, view.getWidth(), 0, 0, 0);
                    resultList.add(animatorSet);
                }
            }
        } else {
            for (int position = oldPosition; position > newPosition; position--) {
                View view = getChildAt(position - getFirstVisiblePosition());
                if ((position + numColumns) % numColumns == 0) {
                    float startX = view.getWidth() * (numColumns - 1);
                    float startY = -view.getHeight();
                    AnimatorSet animatorSet = createAnimations(view, startX, 0, startY, 0);
                    resultList.add(animatorSet);
                } else {
                    AnimatorSet animatorSet = createAnimations(view, -view.getWidth(), 0, 0, 0);
                    resultList.add(animatorSet);
                }
            }
        }

        AnimatorSet resultSet = new AnimatorSet();
        resultSet.playTogether(resultList);
        resultSet.setDuration(300);
        resultSet.setInterpolator(new AccelerateDecelerateInterpolator());
        resultSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mAnimationEnd = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimationEnd = true;
            }
        });
        resultSet.start();
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDisplayHeight = getResources().getDisplayMetrics().heightPixels;
    }

    public void setDragViewListener(DragViewListener dragGridViewListener) {
        this.mListener = dragGridViewListener;
    }


    /**
     * 获取状态栏的高度
     */
    private int getStatusHeight(Context context) {
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        int statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }
}
