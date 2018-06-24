package com.padcmyanmar.sfc.mvp.presenters;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;
import com.padcmyanmar.sfc.mvp.views.NewsListView;

import java.util.List;

public class NewsListPresenter extends BasePresenter<NewsListView>
        implements NewsItemDelegate {

    private MutableLiveData<List<NewsVO>> mNewsListLD;

    @Override
    public void initPresenter(NewsListView view) {
        super.initPresenter(view);
        mNewsListLD = new MutableLiveData<>();
        NewsModel.getInstance().startLoadingMMNews(mNewsListLD, mErrorLD);
    }

    public LiveData<List<NewsVO>> getNewsListLD() {
        return mNewsListLD;
    }

    @Override
    public void onTapComment() {

    }

    @Override
    public void onTapSendTo() {

    }

    @Override
    public void onTapFavorite() {

    }

    @Override
    public void onTapStatistics() {

    }

    @Override
    public void onTapNews(NewsVO news) {
        mView.launchNewsDetailsScreen(news.getNewsId());
    }
}
