package com.padcmyanmar.sfc.mvp.views;

import android.content.Context;

import com.padcmyanmar.sfc.data.vo.NewsVO;

import java.util.List;

/**
 * Created by aung on 1/6/18.
 */

public interface NewsListView {

    void displayNewsList(List<NewsVO> newsList);

    void showLoading();

    void navigateToNewsDetails(NewsVO news);

    Context getContext();

    void showAddNewsScreen();

    void signInGoogle();
}
