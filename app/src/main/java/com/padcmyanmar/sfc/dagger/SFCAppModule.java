package com.padcmyanmar.sfc.dagger;

import android.content.Context;

import com.padcmyanmar.sfc.SFCNewsApp;
import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.mvp.presenters.AddNewsPresenter;
import com.padcmyanmar.sfc.mvp.presenters.NewsListPresenter;
import com.padcmyanmar.sfc.network.MMNewsDataAgent;
import com.padcmyanmar.sfc.network.MMNewsDataAgentImpl;
import com.padcmyanmar.sfc.utils.ConfigUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aung on 1/6/18.
 */

@Module
public class SFCAppModule {

    private SFCNewsApp mApp;

    public SFCAppModule(SFCNewsApp application) {
        mApp = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    public NewsModel provideNewsModel(Context context) {
        return new NewsModel(context);
    }

    @Provides
    public NewsListPresenter provideNewsListPresenter() {
        return new NewsListPresenter();
    }

    @Provides
    public AddNewsPresenter provideAddNewsPresenter() {
        return new AddNewsPresenter();
    }
}
