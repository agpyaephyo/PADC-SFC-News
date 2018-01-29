package com.padcmyanmar.sfc.mvp.presenters;

import com.padcmyanmar.sfc.SFCNewsApp;
import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.mvp.views.AddNewsView;

import javax.inject.Inject;

/**
 * Created by aung on 1/28/18.
 */

public class AddNewsPresenter extends BasePresenter<AddNewsView> {

    @Inject
    NewsModel mNewsModel;

    @Override
    public void onCreate(AddNewsView view) {
        super.onCreate(view);
        SFCNewsApp sfcNewsApp = (SFCNewsApp) mView.getContext();
        sfcNewsApp.getSFCAppComponent().inject(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void onTapPublish(String photoPath, String newsContent) {
        mNewsModel.publishNews(photoPath, newsContent);
        /*
        mNewsModel.uploadFile(photoPath, new NewsModel.UploadFileCallback() {
            @Override
            public void onUploadSucceeded(String uploadedPaths) {
                mView.showUploadedNewsPhoto(uploadedPaths);
            }

            @Override
            public void onUploadFailed(String msg) {
                mView.showErrorMsg(msg);
            }
        });
        */
    }
}
