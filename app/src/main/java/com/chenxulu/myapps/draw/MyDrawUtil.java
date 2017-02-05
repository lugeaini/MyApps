package com.chenxulu.myapps.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.view.View;

import java.util.List;

/**
 * Created by xulu on 2017/2/3.
 */

public class MyDrawUtil {

    /**
     * 清空画板
     *
     * @param view
     * @param canvas
     */
    public static void clearCanvas(View view, Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        view.invalidate();
    }

    /**
     * 绘制一组路径
     *
     * @param view
     * @param canvas
     * @param list
     * @param rect
     */
    public static void drawPathList(View view, Canvas canvas, List<MyPath> list, RectF rect) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        float scale = (rect.right - rect.left) / view.getWidth();
        for (int i = 0; i < list.size(); i++) {
            MyPath myPath = list.get(i);
            List<PointF> tList = myPath.getList();
            Path path = new Path();
            for (int j = 0; j < tList.size(); j++) {
                PointF pointF = tList.get(j);
                float x = pointF.x * scale + rect.left;
                float y = pointF.y * scale + rect.top;
                if (j == 0)
                    path.moveTo(x, y);
                else
                    path.lineTo(x, y);
            }
            canvas.drawPath(path, myPath.getPaint());
        }
        view.invalidate();
    }
}
