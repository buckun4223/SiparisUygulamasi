package com.buckun.siparisuygulamasi.model.core.catagory;

import androidx.annotation.NonNull;

import com.buckun.siparisuygulamasi.model.Kategori;
import com.buckun.siparisuygulamasi.model.catagorypojo.CatagoryList;
import com.buckun.siparisuygulamasi.restApi.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatagoryInteractor implements CatagoryContract.Interactor {


    CatagoryContract.onOptionsListener onOptionsListener;
    List<Kategori> category;

    public CatagoryInteractor(CatagoryContract.onOptionsListener onOptionsListener) {
        this.onOptionsListener = onOptionsListener;
    }


    @Override
    public void performFetchCatagory() {
        NetworkService.getInstance()
                .getJSONApi()
                .fetchCatogry()
                .enqueue(new Callback<CatagoryList>() {
                    @Override
                    public void onResponse(@NonNull Call<CatagoryList> call, @NonNull Response<CatagoryList> response) {
                        CatagoryList catagoryList = response.body();
                        onOptionsListener.onSuccess(catagoryList.getKategoriler());
                    }

                    @Override
                    public void onFailure(@NonNull Call<CatagoryList> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        onOptionsListener.onFail();
                    }
                });
    }



}
