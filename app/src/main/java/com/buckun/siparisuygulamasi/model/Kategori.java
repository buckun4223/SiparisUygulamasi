package com.buckun.siparisuygulamasi.model;

public class Kategori {

    private String CatogryId,CatogryName;

    public Kategori(String catogryId, String catogryName) {
        CatogryId = catogryId;
        CatogryName = catogryName;
    }

    public String getCatogryId() {
        return CatogryId;
    }

    public void setCatogryId(String catogryId) {
        CatogryId = catogryId;
    }

    public String getCatogryName() {
        return CatogryName;
    }

    public void setCatogryName(String catogryName) {
        CatogryName = catogryName;
    }
}
