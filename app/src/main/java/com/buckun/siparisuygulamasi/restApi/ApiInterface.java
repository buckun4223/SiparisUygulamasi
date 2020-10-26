package com.buckun.siparisuygulamasi.restApi;

import com.buckun.siparisuygulamasi.model.catagorypojo.CatagoryList;
import com.buckun.siparisuygulamasi.model.productsPojo.ListProduct;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/json/companyCategory.php?ref=5380f5dbcc3b1021f93ab24c3a1aac24")
    Call<CatagoryList> fetchCatogry();

    @GET("/json/product.php?ref=5380f5dbcc3b1021f93ab24c3a1aac24&start=0")
    Call<ListProduct> fetchProducts();


}
