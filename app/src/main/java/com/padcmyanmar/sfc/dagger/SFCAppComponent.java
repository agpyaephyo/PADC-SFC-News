package com.padcmyanmar.sfc.dagger;

import com.padcmyanmar.sfc.SFCNewsApp;
import com.padcmyanmar.sfc.activities.NewsListActivity;
import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.mvp.presenters.NewsListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by aung on 1/6/18.
 */

@Component(modules = {SFCAppModule.class, UtilsModule.class, NetworkModule.class})
@Singleton
public interface SFCAppComponent {
    void inject(SFCNewsApp app);

    void inject(NewsModel newsModel);

    void inject(NewsListPresenter newsListPresenter);

    void inject(NewsListActivity newsListActivity);
}
