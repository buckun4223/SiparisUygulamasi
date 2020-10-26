package com.buckun.siparisuygulamasi.model.catagorypojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatagoryList {

    @SerializedName("Kategoriler")
    @Expose
    private List<Kategoriler> kategoriler = null;

    public List<Kategoriler> getKategoriler() {
        return kategoriler;
    }

    public void setKategoriler(List<Kategoriler> kategoriler) {
        this.kategoriler = kategoriler;
    }

}