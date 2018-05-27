package com.padcmyanmar.sfc.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;

public class Empty2ViewPod extends RelativeLayout {
    public Empty2ViewPod(Context context) {
        super(context);
    }

    public Empty2ViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Empty2ViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }
}
