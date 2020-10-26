package com.buckun.siparisuygulamasi.presenter.loginpresenter;

import com.buckun.siparisuygulamasi.model.core.login.ILoginContract;
import com.buckun.siparisuygulamasi.model.core.login.LoginInteractor;
import com.buckun.siparisuygulamasi.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LoginPresenter implements ILoginContract.Presenter, ILoginContract.onOptionsListener {

    private ILoginContract.View view;
    private LoginInteractor loginInteractor;

    public LoginPresenter(ILoginContract.View view) {
        this.view = view;
        loginInteractor = new LoginInteractor(this);
    }

    @Override
    public void createNewUserInFirebase(DatabaseReference reference, FirebaseAuth mAuth, User user) {
        loginInteractor.performCreateUser(reference,mAuth, user);

    }

    @Override
    public void loginUserInFirebase(DatabaseReference reference, FirebaseAuth mAuth, User user) {
        loginInteractor.performLoginUser(reference,mAuth,user);
    }

    @Override
    public void onSaveSuccess() {
        view.onCreateUserSuccess();

    }

    @Override
    public void onSaveFail() {
        view.onCreateUserFail();

    }

    @Override
    public void onLoginSuccess() {
        view.onLoginSuccess();
    }

    @Override
    public void onLoginFail() {
        view.onLoginFail();
    }

    @Override
    public void onStart() {
        view.onProcessStart();

    }

    @Override
    public void onStop() {
        view.onProcessStop();

    }
}
