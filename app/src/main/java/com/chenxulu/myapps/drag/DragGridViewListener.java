package com.chenxulu.myapps.drag;

public interface DragGridViewListener {

    void reorderItems(int oldPosition, int newPosition);

    void setHideItem(int hidePosition);

}
