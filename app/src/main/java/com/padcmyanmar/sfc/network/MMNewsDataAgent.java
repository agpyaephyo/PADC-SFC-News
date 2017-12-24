package com.padcmyanmar.sfc.network;

import android.content.Context;

/**
 * Created by aung on 12/3/17.
 */

public interface MMNewsDataAgent {

    void loadMMNews(String accessToken, int pageNo, Context context);
}
