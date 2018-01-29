package com.padcmyanmar.sfc.mvp.views;

import android.content.Context;

/**
 * Created by aung on 1/28/18.
 */

public interface AddNewsView {

    Context getContext();

    void showUploadedNewsPhoto(String photoPath);

    void showErrorMsg(String msg);
}
