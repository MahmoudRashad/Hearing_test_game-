package com.example.ahmed.hearingexpretsystem.Fragment.LoginFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ahmed.hearingexpretsystem.BuildConfig;
import com.example.ahmed.hearingexpretsystem.HomeActivity;
import com.example.ahmed.hearingexpretsystem.R;
import com.example.ahmed.hearingexpretsystem.Sql.DatabaseHelper;
import com.example.ahmed.hearingexpretsystem.Utils.LoginSharedPreferences;

import java.lang.reflect.Method;


/**
 * Created by Ahmed on 5/5/2018.
 */

public class Splash_Fragment extends Fragment {
    private View view;
    private LoginSharedPreferences loginSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_splash, container, false);
        loginSharedPreferences=new LoginSharedPreferences(getActivity());
        waitting();
        return view;
    }
    private void waitting(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // init data base helper class
                DatabaseHelper databaseHelper=new DatabaseHelper(getActivity().getApplicationContext());
                databaseHelper.init(getActivity().getApplicationContext());
                showDebugDBAddressLogToast(getActivity());
                if (loginSharedPreferences.getName()==null||loginSharedPreferences.getName()=="")
                {
                    Tologin();

                }
                else {
                    ToHome();

                }

            }
        }, 3000);
    }

    private void ToHome() {
        getActivity().finish();
        startActivity(new Intent(getActivity(), HomeActivity.class ));
    }


    private void Tologin(){
        getActivity().getFragmentManager().popBackStack();
        Login_Fragmet login_fragmet = new Login_Fragmet();
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contentView, login_fragmet).commit();


    }
    public static void showDebugDBAddressLogToast(Context context) {
        if (BuildConfig.DEBUG) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Method getAddressLog = debugDB.getMethod("getAddressLog");
                Object value = getAddressLog.invoke(null);
                Toast.makeText(context, (String) value, Toast.LENGTH_LONG).show();
                Log.d("address", (String)value);
            } catch (Exception ignore) {

            }
        }
    }
}
