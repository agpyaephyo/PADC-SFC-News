package com.padcmyanmar.sfc;

import android.app.Application;
import android.content.Context;

import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.utils.ConfigUtils;

/**
 * Created by aung on 11/4/17.
 */

public class SFCNewsApp extends Application {

    public static final String LOG_TAG = "SFCNewsApp";

    @Override
    public void onCreate() {
        super.onCreate();
        ConfigUtils.initConfigUtils(getApplicationContext());

        NewsModel.getInstance().startLoadingMMNews(getApplicationContext());
    }
}
