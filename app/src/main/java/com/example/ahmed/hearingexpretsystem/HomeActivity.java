package com.example.ahmed.hearingexpretsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ahmed.hearingexpretsystem.Fragment.LoginFragments.Login_Fragmet;
import com.example.ahmed.hearingexpretsystem.Fragment.LoginFragments.Regist_Fragment;
import com.example.ahmed.hearingexpretsystem.Fragment.LoginFragments.Splash_Fragment;
import com.example.ahmed.hearingexpretsystem.Fragment.MainFragments.Import_Export;
import com.example.ahmed.hearingexpretsystem.Fragment.MainFragments.Select_Patient_History;
import com.example.ahmed.hearingexpretsystem.Fragment.MainFragments.Start_Test;
import com.example.ahmed.hearingexpretsystem.model.Patient_Data;

public class HomeActivity extends AppCompatActivity {

    public static boolean Patient_His_Fragment=true ,Test_Fragment=false, IM_Ex_Fragment =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (Patient_His_Fragment) {
            Select_Patient_History select_patient_history = new Select_Patient_History();
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contentView, select_patient_history).commit();
        }

        if (Test_Fragment) {
            Start_Test start_test = new Start_Test();
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contentView, start_test).commit();
        }

        if (IM_Ex_Fragment) {
            Import_Export import_export = new Import_Export();
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.contentView, import_export).commit();
        }


    }
}
