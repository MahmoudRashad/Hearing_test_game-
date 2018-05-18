package com.example.ahmed.hearingexpretsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ahmed.hearingexpretsystem.Fragment.LoginFragments.Login_Fragmet;
import com.example.ahmed.hearingexpretsystem.Fragment.LoginFragments.Regist_Fragment;
import com.example.ahmed.hearingexpretsystem.Fragment.LoginFragments.Splash_Fragment;

public class MainActivity extends AppCompatActivity {


    public static boolean login_flag=false ,splash_flag=true, regist_flag =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (splash_flag) {
            Splash_Fragment splash_fragment = new Splash_Fragment();
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contentView, splash_fragment).commit();
        }

        if (login_flag) {
            Login_Fragmet login_fragmet = new Login_Fragmet();
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contentView, login_fragmet).commit();
        }

        if (regist_flag) {
            Regist_Fragment regist_fragment = new Regist_Fragment();
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contentView, regist_fragment).commit();
        }


    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 2 ){
            getFragmentManager().popBackStack();
        } else {
            finish();

        }
    }
}
