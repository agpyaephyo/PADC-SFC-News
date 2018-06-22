package com.padcmyanmar.sfc.mvp.views;

import com.padcmyanmar.sfc.data.vo.NewsVO;

public interface NewsDetailsView extends BaseView {

    void displayNewsDetails(NewsVO news);
}
