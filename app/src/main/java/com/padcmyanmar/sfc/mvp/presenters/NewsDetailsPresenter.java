package com.padcmyanmar.sfc.mvp.presenters;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.mvp.views.NewsDetailsView;

public class NewsDetailsPresenter extends BasePresenter<NewsDetailsView> {

    @Override
    public void initPresenter(NewsDetailsView mView) {
        super.initPresenter(mView);
    }

    public LiveData<NewsVO> onUiReady(String newsId) {
        return NewsModel.getInstance().getNewsByIdLD(newsId);
    }
}
