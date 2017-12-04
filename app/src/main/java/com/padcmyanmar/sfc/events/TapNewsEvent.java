package com.padcmyanmar.sfc.events;

/**
 * Created by aung on 12/2/17.
 */

public class TapNewsEvent {

    private String newsId;

    public TapNewsEvent(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsId() {
        return newsId;
    }
}
