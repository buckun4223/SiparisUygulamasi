package com.buckun.siparisuygulamasi.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.buckun.siparisuygulamasi.R;
import com.buckun.siparisuygulamasi.model.User;


public class CreateUserDialog extends AppCompatDialogFragment {

    EditText email;
    EditText age;
    EditText password;
    Button saveButton;

    AlertDialog.Builder builder;

    public createDialogListenerForLogin createDialogListenerForLogin;
    User user;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view);
        builder.setTitle("Add New User");
        builder.setCancelable(true);
        email = view.findViewById(R.id.email_adress);
        age = view.findViewById(R.id.age);
        password = view.findViewById(R.id.password_password);
        saveButton = view.findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User(email.getText().toString(),
                        password.getText().toString(),
                        age.getText().toString(), "key");
                createDialogListenerForLogin.saveUser(user);
                dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        createDialogListenerForLogin = (CreateUserDialog.createDialogListenerForLogin) context;
    }

    public interface createDialogListenerForLogin {
        void saveUser(User user);
    }
}
