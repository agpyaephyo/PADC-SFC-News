package com.padcmyanmar.sfc.network;

import android.content.Context;

import com.google.gson.Gson;
import com.padcmyanmar.sfc.events.RestApiEvents;
import com.padcmyanmar.sfc.network.reponses.GetNewsResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aung on 12/3/17.
 */

public class MMNewsDataAgentImpl implements MMNewsDataAgent {

    private static MMNewsDataAgentImpl objInstance;

    private MMNewsAPI theAPI;

    private MMNewsDataAgentImpl() {
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

        theAPI = retrofit.create(MMNewsAPI.class);

    }

    public static MMNewsDataAgentImpl getInstance() {
        if (objInstance == null) {
            objInstance = new MMNewsDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadMMNews(String accessToken, int pageNo, final Context context) {
        Call<GetNewsResponse> loadMMNewsCall = theAPI.loadMMNews(pageNo, accessToken);
        loadMMNewsCall.enqueue(new SFCCallback<GetNewsResponse>() {
            @Override
            public void onResponse(Call<GetNewsResponse> call, Response<GetNewsResponse> response) {
                super.onResponse(call, response);
                GetNewsResponse getNewsResponse = response.body();
                if (getNewsResponse != null
                        && getNewsResponse.getNewsList().size() > 0) {
                    RestApiEvents.NewsDataLoadedEvent newsDataLoadedEvent = new RestApiEvents.NewsDataLoadedEvent(
                            getNewsResponse.getPageNo(), getNewsResponse.getNewsList(), context);
                    EventBus.getDefault().post(newsDataLoadedEvent);
                }
            }
        });
    }
}
