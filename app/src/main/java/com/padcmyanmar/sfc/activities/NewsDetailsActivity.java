package com.padcmyanmar.sfc.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.adapters.NewsImagesPagerAdapter;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.persistence.MMNewsContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aung on 11/11/17.
 */

public class NewsDetailsActivity extends BaseActivity
        implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String IE_NEWS_ID = "IE_NEWS_ID";
    private static final int NEWS_DETAILS_LOADER_ID = 1002;

    @BindView(R.id.vp_news_details_images)
    ViewPager vpNewsDetailsImages;

    @BindView(R.id.iv_publication_logo)
    ImageView ivPublicationLogo;

    @BindView(R.id.tv_publication_name)
    TextView tvPublicationName;

    @BindView(R.id.tv_published_date)
    TextView tvPublishedDate;

    @BindView(R.id.tv_news_details)
    TextView tvNewsDetails;

    private String mNewsId;
    private NewsImagesPagerAdapter mNewsImagesPagerAdapter;

    /**
     * Create intent object to start NewsDetailsActivity.
     *
     * @param context
     * @param newsId  : id for news object.
     * @return
     */
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

        mNewsImagesPagerAdapter = new NewsImagesPagerAdapter(getApplicationContext());
        vpNewsDetailsImages.setAdapter(mNewsImagesPagerAdapter);

        vpNewsDetailsImages.setOffscreenPageLimit(mNewsImagesPagerAdapter.getCount());

        mNewsId = getIntent().getStringExtra(IE_NEWS_ID);
        if (TextUtils.isEmpty(mNewsId)) {
            throw new UnsupportedOperationException("newsId required for NewsDetailsActivity");
        } else {
            getSupportLoaderManager().initLoader(NEWS_DETAILS_LOADER_ID, null, this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getApplicationContext(),
                MMNewsContract.NewsEntry.CONTENT_URI,
                null,
                MMNewsContract.NewsEntry.COLUMN_NEWS_ID + " = ?", new String[]{mNewsId},
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null && data.moveToFirst()) {
            NewsVO news = NewsVO.parseFromCursor(getApplicationContext(), data);
            bindData(news);
        }
    }

    private void bindData(NewsVO news) {
        tvPublicationName.setText(news.getPublication().getTitle());
        tvPublishedDate.setText(news.getPostedDate());
        tvNewsDetails.setText(news.getDetails());

        Glide.with(this)
                .load(news.getPublication().getLogo())
                .into(ivPublicationLogo);

        if(news.getImages().isEmpty()) {

        } else {
            mNewsImagesPagerAdapter.setImages(news.getImages());
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
