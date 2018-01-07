package com.padcmyanmar.sfc.dagger;

import android.content.Context;

import com.padcmyanmar.sfc.utils.ConfigUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aung on 1/7/18.
 */
@Module
public class UtilsModule {

    @Provides
    @Singleton
    public ConfigUtils provideConfigUtils(Context context) {
        return new ConfigUtils(context);
    }
}
