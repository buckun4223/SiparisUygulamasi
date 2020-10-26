package com.buckun.siparisuygulamasi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.buckun.siparisuygulamasi.R;
import com.buckun.siparisuygulamasi.model.core.login.ILoginContract;
import com.buckun.siparisuygulamasi.presenter.loginpresenter.LoginPresenter;
import com.buckun.siparisuygulamasi.model.User;


import com.buckun.siparisuygulamasi.utils.Config;
import com.buckun.siparisuygulamasi.utils.CreateUserDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements CreateUserDialog.createDialogListenerForLogin, ILoginContract.View {

    EditText inputEmail;
    EditText inputPassword;
    Button loginButton;
    Button registerButton;
    LoginPresenter iLoginPresenter;
    ProgressDialog progressBar;

    public DatabaseReference databaseReference;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassWord);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registertionButton);
        iLoginPresenter = new LoginPresenter(this);
        progressBar = new ProgressDialog(LoginActivity.this);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(Config.USER_NODE);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            Intent i = new Intent(this, CategoryActivity.class);
            i.putExtra("email",mAuth.getCurrentUser().getEmail());
            startActivity(i);
            finish();
        }
    }

    public void onLogin(View view) {
        progressBar.show();
        User newUser = new User(inputEmail.getText().toString(),inputPassword.getText().toString());
        iLoginPresenter.loginUserInFirebase(databaseReference,mAuth,newUser);
    }

    public void onRegisteration(View view) {
        openDialog();
    }

    private void openDialog() {
        CreateUserDialog createUserDialog = new CreateUserDialog();
        createUserDialog.show(getSupportFragmentManager(),"Create Dialog");
    }

    @Override
    public void saveUser(User user) {
        User newUser = new User(user.getEmail(),user.getPassWord(),user.getAge(),databaseReference.getKey());
        iLoginPresenter.createNewUserInFirebase(databaseReference,mAuth,newUser);
    }

    @Override
    public void onLoginSuccess() {
        progressBar.dismiss();
        Intent i = new Intent(this, CategoryActivity.class);
        i.putExtra("email",inputEmail.getText().toString());
        startActivity(i);
        finish();
    }

    @Override
    public void onLoginFail() {
        progressBar.dismiss();
        Toast.makeText(this,"Login fail",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onCreateUserSuccess() {
        Toast.makeText(this,"save success",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateUserFail() {
        Toast.makeText(this,"save fail",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProcessStart() {

    }

    @Override
    public void onProcessStop() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}