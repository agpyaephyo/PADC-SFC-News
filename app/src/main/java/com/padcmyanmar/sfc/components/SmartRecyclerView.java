package com.padcmyanmar.sfc.components;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by aung on 11/25/17.
 */

public class SmartRecyclerView extends RecyclerView {

    private View mEmptyView;

    private AdapterDataObserver dataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            checkIfEmpty();
        }
    };

    public SmartRecyclerView(Context context) {
        super(context);
    }

    public SmartRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(dataObserver);
        }

        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(dataObserver);
        }
        checkIfEmpty();
    }

    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    /**
     * check if adapter connected to SRV is empty. If so, show emptyView.
     */
    private void checkIfEmpty() {
        boolean isEmpty = getAdapter().getItemCount() == 0;
        if (mEmptyView != null) {
            mEmptyView.setVisibility(isEmpty ? View.VISIBLE : View.INVISIBLE);
            setVisibility(isEmpty ? View.INVISIBLE : View.VISIBLE);
        }
    }
}
