package com.padcmyanmar.sfc.data.models;

import com.google.gson.Gson;
import com.padcmyanmar.sfc.network.MMNewsAPI;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseModel {

    protected MMNewsAPI mTheApi;

    public BaseModel() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/mm-news/apis/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        mTheApi = retrofit.create(MMNewsAPI.class);
    }
}
