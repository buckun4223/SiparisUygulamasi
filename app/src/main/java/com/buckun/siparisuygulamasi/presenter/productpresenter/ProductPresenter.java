package com.buckun.siparisuygulamasi.presenter.productpresenter;

import com.buckun.siparisuygulamasi.model.catagorypojo.Kategoriler;
import com.buckun.siparisuygulamasi.model.core.product.ProductContract;
import com.buckun.siparisuygulamasi.model.core.product.ProductInteractor;
import com.buckun.siparisuygulamasi.model.productsPojo.Bilgiler;

import java.util.List;

public class ProductPresenter implements ProductContract.Presenter, ProductContract.onOptionsListener {


    ProductContract.View view;
    ProductInteractor productInteractor;

    public ProductPresenter(ProductContract.View view) {
        this.view = view;
        productInteractor = new ProductInteractor(this);
    }

    @Override
    public void fetchProduct() {
        productInteractor.performFetchProduct();

    }

    @Override
    public void onSuccess(List<Bilgiler> bilgilers) {
        view.onFetchSuccess(bilgilers);

    }

    @Override
    public void onFail() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
