package com.buckun.siparisuygulamasi.presenter.catagorypresenter;
import com.buckun.siparisuygulamasi.model.catagorypojo.CatagoryList;
import com.buckun.siparisuygulamasi.model.catagorypojo.Kategoriler;
import com.buckun.siparisuygulamasi.model.core.catagory.CatagoryContract;
import com.buckun.siparisuygulamasi.model.core.catagory.CatagoryInteractor;

import java.util.List;

public class CatagoryPresenter  implements CatagoryContract.Presenter, CatagoryContract.onOptionsListener {


    CatagoryContract.View view;
    CatagoryInteractor catagoryInteractor;

    public CatagoryPresenter(CatagoryContract.View view) {
        this.view = view;
        catagoryInteractor = new CatagoryInteractor(this);
    }

    @Override
    public void fetchCatagory() {
        catagoryInteractor.performFetchCatagory();
    }

    @Override
    public void onSuccess(List<Kategoriler> catagoryList) {
        view.onFetchSuccess(catagoryList);
    }

    @Override
    public void onFail() {
        view.onFetchFail();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
