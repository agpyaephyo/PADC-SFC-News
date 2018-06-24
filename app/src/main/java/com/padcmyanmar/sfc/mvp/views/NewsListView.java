package com.padcmyanmar.sfc.mvp.views;

import com.padcmyanmar.sfc.data.vo.NewsVO;

import java.util.List;

public interface NewsListView extends BaseView {

    void launchNewsDetailsScreen(String newsId);
}
