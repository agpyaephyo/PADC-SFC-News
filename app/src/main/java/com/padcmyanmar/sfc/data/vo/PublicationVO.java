package com.padcmyanmar.sfc.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aung on 12/3/17.
 */

public class PublicationVO {

    @SerializedName("publication-id")
    private String publicationId;

    @SerializedName("title")
    private String title;

    @SerializedName("logo")
    private String logo;

    public String getPublicationId() {
        return publicationId;
    }

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }
}
