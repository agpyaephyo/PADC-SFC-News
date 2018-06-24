package com.padcmyanmar.sfc.mvp.presenters;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.mvp.views.NewsDetailsView;

public class NewsDetailsPresenter extends BasePresenter<NewsDetailsView> {

    private MutableLiveData<NewsVO> mNewsLD;

    @Override
    public void initPresenter(NewsDetailsView mView) {
        super.initPresenter(mView);
        mNewsLD = new MutableLiveData<>();
    }

    public LiveData<NewsVO> onUiReady(String newsId) {
        NewsVO news = NewsModel.getInstance().getNewsById(newsId);
        mNewsLD.setValue(news);
        return mNewsLD;
    }
}
