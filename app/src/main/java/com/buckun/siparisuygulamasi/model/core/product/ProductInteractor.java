package com.buckun.siparisuygulamasi.model.core.product;

import androidx.annotation.NonNull;

import com.buckun.siparisuygulamasi.model.productsPojo.Bilgiler;
import com.buckun.siparisuygulamasi.model.productsPojo.ListProduct;
import com.buckun.siparisuygulamasi.model.productsPojo.Product;
import com.buckun.siparisuygulamasi.restApi.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductInteractor implements ProductContract.Interactor {

    private ProductContract.onOptionsListener onOptionsListener;

    public ProductInteractor(ProductContract.onOptionsListener onOptionsListener) {
        this.onOptionsListener = onOptionsListener;
    }

    @Override
    public void performFetchProduct() {
        NetworkService.getInstance()
                .getJSONApi()
                .fetchProducts()
                .enqueue(new Callback<ListProduct>() {
                    @Override
                    public void onResponse(@NonNull Call<ListProduct> call, @NonNull Response<ListProduct> response) {
                        List<Bilgiler> bilgiler = response.body().getProducts().get(0).getBilgiler();
                        onOptionsListener.onSuccess(bilgiler);

                    }
                    @Override
                    public void onFailure(@NonNull Call<ListProduct> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        onOptionsListener.onFail();
                    }
                });

    }
}
