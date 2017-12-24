package com.padcmyanmar.sfc.events;

import android.content.Context;

import com.padcmyanmar.sfc.data.vo.NewsVO;

import java.util.List;

/**
 * Created by aung on 12/2/17.
 */

public class RestApiEvents {

    public static class EmptyResponseEvent {

    }

    public static class ErrorInvokingAPIEvent {
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class NewsDataLoadedEvent {
        private int loadedPageIndex;
        private List<NewsVO> loadNews;
        private Context context;

        public NewsDataLoadedEvent(int loadedPageIndex, List<NewsVO> loadNews, Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadNews = loadNews;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<NewsVO> getLoadNews() {
            return loadNews;
        }

        public Context getContext() {
            return context;
        }
    }
}
