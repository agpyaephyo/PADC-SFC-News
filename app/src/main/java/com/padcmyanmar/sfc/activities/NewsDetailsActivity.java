package com.padcmyanmar.sfc.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.adapters.NewsImagesPagerAdapter;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.mvp.presenters.NewsDetailsPresenter;
import com.padcmyanmar.sfc.mvp.views.NewsDetailsView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aung on 11/11/17.
 */

public class NewsDetailsActivity extends BaseActivity
        implements NewsDetailsView {

    private static final String IE_NEWS_ID = "IE_NEWS_ID";

    @BindView(R.id.vp_news_details_images)
    ViewPager vpNewsDetailsImages;

    @BindView(R.id.tv_news_details)
    TextView tvNewsDetails;

    private NewsDetailsPresenter mPresenter;

    public static Intent newIntent(Context context, String newsId) {
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra(IE_NEWS_ID, newsId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this, this);

        NewsImagesPagerAdapter newsImagesPagerAdapter = new NewsImagesPagerAdapter(getApplicationContext());
        vpNewsDetailsImages.setAdapter(newsImagesPagerAdapter);

        mPresenter = ViewModelProviders.of(this).get(NewsDetailsPresenter.class);
        mPresenter.initPresenter(this);
        mPresenter.getErrorLD().observe(this, this);

        String newsId = getIntent().getStringExtra(IE_NEWS_ID);
        mPresenter.onUiReady(newsId).observe(this, new Observer<NewsVO>() {
            @Override
            public void onChanged(@Nullable NewsVO newsVO) {
                displayNewsDetails(newsVO);
            }
        });
    }

    private void displayNewsDetails(NewsVO news) {
        tvNewsDetails.setText(news.getDetails());
    }

    @Override
    public void displayErrorMsg(String errorMsg) {

    }
}
