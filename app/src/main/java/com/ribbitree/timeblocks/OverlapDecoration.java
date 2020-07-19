package com.ribbitree.timeblocks;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OverlapDecoration extends RecyclerView.ItemDecoration {

    private final static int vertOverlap = -85;

    @Override
    public void getItemOffsets (@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        int itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition != 0) {
            outRect.set(0, vertOverlap, 0, 0);
        }

    }
}