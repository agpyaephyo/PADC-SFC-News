package com.padcmyanmar.sfc.network;

import com.padcmyanmar.sfc.network.reponses.GetNewsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by aung on 12/3/17.
 */

public interface MMNewsAPI {

    @FormUrlEncoded
    @POST("v1/getMMNews.php")
    Call<GetNewsResponse> loadMMNews(
            @Field("page") int pageIndex,
            @Field("access_token") String accessToken);
}
