package com.buckun.siparisuygulamasi.model.catagorypojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("CatogryId")
    @Expose
    private String catogryId;
    @SerializedName("TopCatogryId")
    @Expose
    private String topCatogryId;
    @SerializedName("CatogryName")
    @Expose
    private String catogryName;

    public String getCatogryId() {
        return catogryId;
    }

    public void setCatogryId(String catogryId) {
        this.catogryId = catogryId;
    }

    public String getTopCatogryId() {
        return topCatogryId;
    }

    public void setTopCatogryId(String topCatogryId) {
        this.topCatogryId = topCatogryId;
    }

    public String getCatogryName() {
        return catogryName;
    }

    public void setCatogryName(String catogryName) {
        this.catogryName = catogryName;
    }

}