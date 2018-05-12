package com.example.ahmed.hearingexpretsystem.Fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
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

import com.example.ahmed.hearingexpretsystem.Fragments.Utils.LoginSharedPreferences;
import com.example.ahmed.hearingexpretsystem.HomeActivity;
import com.example.ahmed.hearingexpretsystem.R;
import com.example.ahmed.hearingexpretsystem.Sql.DatabaseHelper;
import com.example.ahmed.hearingexpretsystem.model.User;

/**
 * Created by Ahmed on 5/5/2018.
 */

public class Regist_Fragment extends Fragment {
    private View view;
    EditText Name,Password,Confirm_Password,Email;
    TextView link_sigin;
    Button Register;
    LoginSharedPreferences preferences;
    private String Name_str,Password_str,Email_str;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_register, container, false);
        init();
        Clicks();
        callserver();

        return view;
    }

    private void callserver() {
    }

    private void Clicks() {

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()){
                }else {

                }
            }
        });
        link_sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login_Fragmet login_fragmet = new Login_Fragmet();
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contentView, login_fragmet).commit();

            }
        });

    }

    private boolean LoginServer() {
        // save this user in database

        return true;
    }

    private void init() {

        Name = view.findViewById(R.id.Name);
        Password = view.findViewById(R.id.Password);
        Confirm_Password = view.findViewById(R.id.Conf_Password);
        Email=view.findViewById(R.id.Email);
        Register = view.findViewById(R.id.Regist_btn);
        link_sigin=view.findViewById(R.id.link_sigin);


    }

    private boolean validation() {
        if (Name.getText().toString().length()<1)
        {
            Name.setError(getString(R.string.NameError));
            return false;
        }
        if (Password.getText().toString().length()<1)
        {
            Password.setError(getString(R.string.Pass_Error));
            return false;
        }
        if (Confirm_Password.getText().toString().length()<1)
        {
            Confirm_Password.setError(getString(R.string.Conf_Pass_Error));
            return false;
        }
        else if (Password.getText().toString().length()!=Confirm_Password.getText().toString().length()){
            Confirm_Password.setError(getString(R.string.Conf_Pass_Match_Error));
            return false;
        }
        postDataToSQLite();
        return true;
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {

        if (!DatabaseHelper.getInstance().checkUser(Email.getText().toString().trim())) {
            user= new User();
            user.setName(Name.getText().toString().trim());
            user.setEmail(Email.getText().toString().trim());
            user.setPassword(Password.getText().toString().trim());

            DatabaseHelper.getInstance().addUser(user);


            // Snack Bar to show success message that record saved successfully
//            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
//            Regist_Fragment regist_fragment = new Regist_Fragment();
//            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contentView, regist_fragment).commit();


            Intent accountsIntent = new Intent(getActivity(), HomeActivity.class);
            emptyInputEditText();
            startActivity(accountsIntent);



        } else {
            // Snack Bar to show error message that record already exists
//            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        Name.setText(null);
        Email.setText(null);
        Password.setText(null);
        Confirm_Password.setText(null);
    }

}
