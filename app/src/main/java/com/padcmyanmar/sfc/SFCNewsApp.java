package com.padcmyanmar.sfc;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.padcmyanmar.sfc.dagger.DaggerSFCAppComponent;
import com.padcmyanmar.sfc.dagger.NetworkModule;
import com.padcmyanmar.sfc.dagger.SFCAppComponent;
import com.padcmyanmar.sfc.dagger.SFCAppModule;
import com.padcmyanmar.sfc.dagger.UtilsModule;
import com.padcmyanmar.sfc.data.models.NewsModel;

import javax.inject.Inject;

/**
 * Created by aung on 11/4/17.
 */

public class SFCNewsApp extends Application {

    public static final String LOG_TAG = "SFCNewsApp";

    private SFCAppComponent mSFCAppComponent;

    @Inject
    Context mContext;

    @Inject
    NewsModel mNewsModel;

    @Override
    public void onCreate() {
        super.onCreate();

        mSFCAppComponent = initDagger(); //dagger init
        mSFCAppComponent.inject(this); //register consumer

        mNewsModel.startLoadingMMNews(getApplicationContext());

        Log.d(LOG_TAG, "mContext : " + mContext);
    }

    private SFCAppComponent initDagger() {
        //return null;
        return DaggerSFCAppComponent.builder()
                .sFCAppModule(new SFCAppModule(this))
                .utilsModule(new UtilsModule())
                .networkModule(new NetworkModule())
                .build();

    }

    public SFCAppComponent getSFCAppComponent() {
        return mSFCAppComponent;
    }
}
