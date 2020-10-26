package com.buckun.siparisuygulamasi.model.core.login;

import com.buckun.siparisuygulamasi.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public interface ILoginContract {


    interface View {
        void onLoginSuccess();

        void onLoginFail();

        void onCreateUserSuccess();

        void onCreateUserFail();

        void onProcessStart();

        void onProcessStop();

    }

    interface Presenter {
        void createNewUserInFirebase(DatabaseReference reference, FirebaseAuth mAuth, User user);

        void loginUserInFirebase(DatabaseReference reference, FirebaseAuth mAuth, User user);

    }

    interface Interactor {
        void performCreateUser(DatabaseReference reference, FirebaseAuth mAuth, User user);

        void performLoginUser(DatabaseReference reference, FirebaseAuth mAuth, User user);
    }

    interface onOptionsListener {
        void onSaveSuccess();

        void onSaveFail();

        void onLoginSuccess();

        void onLoginFail();

        void onStart();

        void onStop();
    }

}
