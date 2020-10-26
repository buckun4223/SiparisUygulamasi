package com.buckun.siparisuygulamasi.model.catagorypojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kategoriler {

    @SerializedName("durum")
    @Expose
    private Boolean durum;
    @SerializedName("mesaj")
    @Expose
    private String mesaj;

    @SerializedName("Categories")
    @Expose
    private List<Category> categories = null;

    public Boolean getDurum() {
        return durum;
    }

    public void setDurum(Boolean durum) {
        this.durum = durum;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
