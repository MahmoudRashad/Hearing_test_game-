package com.example.ahmed.hearingexpretsystem.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ahmed.hearingexpretsystem.HomeActivity;
import com.example.ahmed.hearingexpretsystem.R;
import com.example.ahmed.hearingexpretsystem.Sql.DatabaseHelper;

/**
 * Created by Ahmed on 5/5/2018.
 */

public class Login_Fragmet extends Fragment {
    TextView link_signup;
    EditText Name, Password;
    CheckBox saveLoginCheckBox;
    Button Login;
    private View view;

    ///var
    String Name_str, Password_str;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_login, container, false);
        init();
        clicks();
        callserver();
        return view;
    }

    private void callserver() {
    }

    private void clicks() {
        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist_Fragment regist_fragment = new Regist_Fragment();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contentView, regist_fragment).commit();

            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
//                    Name_str = Name.getText().toString();
//                    Password_str = Password.getText().toString();
                    verifyFromSQLite();
                }
            }
        });
    }

    private boolean validation() {
        if (Name.getText().toString().length() < 1) {
            Name.setError(getString(R.string.NameError));
            return false;
        }
        if (Password.getText().toString().length() < 1) {
            Name.setError(getString(R.string.Pass_Error));
            return false;
        }
        return true;
    }

    private void init() {
        link_signup = view.findViewById(R.id.link_signup);
        Name = view.findViewById(R.id.Name);
        Password = view.findViewById(R.id.Password);
        saveLoginCheckBox = view.findViewById(R.id.saveLoginCheckBox);
        Login = view.findViewById(R.id.Login);


    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {

        if (DatabaseHelper.getInstance().checkUser(Name.getText().toString().trim()
                , Password.getText().toString().trim())) {


            Intent accountsIntent = new Intent(getActivity(), HomeActivity.class);
            emptyInputEditText();
            startActivity(accountsIntent);
//            Regist_Fragment regist_fragment = new Regist_Fragment();
//            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contentView, regist_fragment).commit();
            emptyInputEditText();


        } else {

            // Snack Bar to show success message that record is wrong
//            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        Name.setText(null);
        Password.setText(null);
    }

}
