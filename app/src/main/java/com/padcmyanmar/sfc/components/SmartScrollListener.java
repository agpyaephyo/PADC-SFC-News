package com.padcmyanmar.sfc.components;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by aung on 11/26/17.
 */

public class SmartScrollListener extends RecyclerView.OnScrollListener {

    public interface OnSmartScrollListener {
        void onListEndReach();
    }

    private boolean isListEndReached = false;

    private OnSmartScrollListener mSmartScrollListener;

    public SmartScrollListener(OnSmartScrollListener smartScrollListener) {
        this.mSmartScrollListener = smartScrollListener;
    }

    @Override
    public void onScrolled(RecyclerView rv, int dx, int dy) {
        super.onScrolled(rv, dx, dy);

        int visibleItemCount = rv.getLayoutManager().getChildCount();
        int totalItemCount = rv.getLayoutManager().getItemCount();
        int pastVisibleItems = ((LinearLayoutManager) rv.getLayoutManager()).findFirstVisibleItemPosition();

        if ((visibleItemCount + pastVisibleItems) < totalItemCount) {
            isListEndReached = false;
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
        super.onScrollStateChanged(recyclerView, scrollState);
        if (scrollState == RecyclerView.SCROLL_STATE_IDLE
                && ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1
                && !isListEndReached) {
            isListEndReached = true;
            mSmartScrollListener.onListEndReach();
        }
    }
}
