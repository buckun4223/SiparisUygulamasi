package com.buckun.siparisuygulamasi.model.core.catagory;

import com.buckun.siparisuygulamasi.model.catagorypojo.CatagoryList;
import com.buckun.siparisuygulamasi.model.catagorypojo.Kategoriler;

import java.util.List;

public interface CatagoryContract {

    interface View {

        void onFetchSuccess(List<Kategoriler> catagoryList);

        void onFetchFail();

        void onProcessStart();

        void onProcessStop();

    }

    interface Presenter {

        void fetchCatagory();

    }

    interface Interactor {

        void performFetchCatagory();
    }

    interface onOptionsListener {

        void onSuccess(List<Kategoriler> catagoryList);

        void onFail();

        void onStart();

        void onStop();
    }
}
