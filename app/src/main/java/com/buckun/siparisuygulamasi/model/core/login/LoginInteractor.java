package com.buckun.siparisuygulamasi.model.core.login;


import androidx.annotation.NonNull;

import com.buckun.siparisuygulamasi.model.User;
import com.buckun.siparisuygulamasi.model.core.login.ILoginContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LoginInteractor implements ILoginContract.Interactor {

    private ILoginContract.onOptionsListener onOptionsListener;

    public LoginInteractor(ILoginContract.onOptionsListener onOptionsListener) {
        this.onOptionsListener = onOptionsListener;
    }

    @Override
    public void performCreateUser(final DatabaseReference reference, FirebaseAuth mAuth, final User user) {
        if (user.isValidDate()) {
            mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassWord()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        reference.child(user.getKey()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    onOptionsListener.onSaveSuccess();

                                } else {
                                    onOptionsListener.onSaveFail();
                                }
                            }
                        });
                    }else {
                        onOptionsListener.onSaveFail();
                    }
                }
            });
        } else {
            onOptionsListener.onSaveFail();
        }


    }

    @Override
    public void performLoginUser(DatabaseReference reference, FirebaseAuth mAuth, User user) {
        if (user.isValidDate()) {
            mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassWord())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                onOptionsListener.onLoginSuccess();
                            } else {
                                onOptionsListener.onLoginFail();
                            }
                        }
                    });
        } else {
            onOptionsListener.onLoginFail();
        }
    }
}
