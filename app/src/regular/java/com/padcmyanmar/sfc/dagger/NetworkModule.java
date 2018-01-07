package com.padcmyanmar.sfc.dagger;

import com.padcmyanmar.sfc.network.MMNewsDataAgent;
import com.padcmyanmar.sfc.network.MMNewsDataAgentImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aung on 1/7/18.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public MMNewsDataAgent provideMMNewsDataAgent() {
        return new MMNewsDataAgentImpl();
    }
}
