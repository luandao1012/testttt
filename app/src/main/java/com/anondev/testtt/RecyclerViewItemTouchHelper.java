package com.anondev.testtt;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private SwipedCallback swipedCallback;

    public RecyclerViewItemTouchHelper(int dragDirs, int swipeDirs, SwipedCallback swipedCallback) {
        super(dragDirs, swipeDirs);
        this.swipedCallback = swipedCallback;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        swipedCallback.setSwipedListener(viewHolder);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foreground = ((Adapter.ItemViewHolder) viewHolder).getView();
        getDefaultUIUtil().onDraw(c, recyclerView, foreground, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        View foreground = ((Adapter.ItemViewHolder) viewHolder).getView();
        getDefaultUIUtil().clearView(foreground);
    }

    interface SwipedCallback {
        void setSwipedListener(RecyclerView.ViewHolder viewHolder);
    }
}
