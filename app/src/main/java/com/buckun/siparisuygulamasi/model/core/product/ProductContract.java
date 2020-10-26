package com.buckun.siparisuygulamasi.model.core.product;

import com.buckun.siparisuygulamasi.model.catagorypojo.Kategoriler;
import com.buckun.siparisuygulamasi.model.productsPojo.Bilgiler;

import java.util.List;

public interface ProductContract {
    interface View {

        void onFetchSuccess(List<Bilgiler> bilgilers);

        void onFetchFail();

        void onProcessStart();

        void onProcessStop();

    }

    interface Presenter {

        void fetchProduct();

    }

    interface Interactor {

        void performFetchProduct();
    }

    interface onOptionsListener {

        void onSuccess(List<Bilgiler> bilgilers);

        void onFail();

        void onStart();

        void onStop();
    }
}
