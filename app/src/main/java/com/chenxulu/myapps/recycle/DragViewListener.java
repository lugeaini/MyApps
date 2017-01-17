package com.chenxulu.myapps.recycle;

public interface DragViewListener {

    void reorderItems(int oldPosition, int newPosition);

    void setHideItem(int hidePosition);

    boolean canDrag(int position);

}
