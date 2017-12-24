package com.padcmyanmar.sfc.delegates;

import com.padcmyanmar.sfc.data.vo.NewsVO;

/**
 * Created by aung on 11/11/17.
 */

public interface NewsItemDelegate {
    void onTapComment();
    void onTapSendTo();
    void onTapFavorite();
    void onTapStatistics();
    void onTapNews(NewsVO news);
}
