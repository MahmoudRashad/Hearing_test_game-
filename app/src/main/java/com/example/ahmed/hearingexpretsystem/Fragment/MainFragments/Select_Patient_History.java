package com.example.ahmed.hearingexpretsystem.Fragment.MainFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ahmed.hearingexpretsystem.R;
import com.example.ahmed.hearingexpretsystem.Sql.DatabaseHelper;
import com.example.ahmed.hearingexpretsystem.model.Patient_Data;
import com.example.ahmed.hearingexpretsystem.model.Patient_List_Adapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.ahmed.hearingexpretsystem.Sql.DatabaseHelper.COLUMN_PATIENT_ID;
import static com.example.ahmed.hearingexpretsystem.Sql.DatabaseHelper.COLUMN_PATIENT_NAME;
import static com.example.ahmed.hearingexpretsystem.Sql.DatabaseHelper.COLUMN_TEST_ID;
import static com.example.ahmed.hearingexpretsystem.Sql.DatabaseHelper.COLUMN_TEST_PATIENT_ID;
import static com.example.ahmed.hearingexpretsystem.Sql.DatabaseHelper.TABLE_PATIENT;
import static com.example.ahmed.hearingexpretsystem.Sql.DatabaseHelper.TABLE_TEST;


/**
 * Created by Ahmed on 5/17/2018.
 */

public class Select_Patient_History extends Fragment {
    View view;

    EditText Patient_name;
    ImageView Search;
    ListView Patient_Information;
    ArrayList<Patient_Data> patient_data=new ArrayList<>();
    DatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.get_patient_history, container, false);
        init();
        Clicks();
        callserver();

        return view;
    }

    private void callserver() {
        ///todo get data from database

//        for (int i = 0; i <5 ; i++) {
//            patient_data.add(new Patient_Data(i,"ahmed"+i,"5","cafetria","9","ahmed"+i));
//        }

        if (Patient_name.getText().toString()!=null){
            db=new DatabaseHelper(getActivity());
//            String selectpatientquery="SELECT * FROM "+TABLE_PATIENT +" WHERE "+COLUMN_PATIENT_NAME+" = '"+Patient_name.getText().toString()+"';";
           String selecttests_of_pation  = "SELECT * FROM "+TABLE_PATIENT +" p, " +TABLE_TEST+
                   " t where p."+COLUMN_PATIENT_ID+" = t."+COLUMN_TEST_PATIENT_ID+" and " +
                   " p."+COLUMN_PATIENT_NAME+
                   " = '"+Patient_name.getText().toString()+"';";
            List<Object> data= db.select(selecttests_of_pation);
//            JSONObject a =data;
            List<Patient_Data> patients = new ArrayList<>();
            for (int i = 0; i <data.size() ; i++) {
                patients.add((Patient_Data) data.get(i));
            }

            Patient_List_Adapter adapter = new Patient_List_Adapter(getActivity(),patient_data);
            Patient_Information.setAdapter(adapter);
//        Patient_Information.notify();

        }
    }

    private void Clicks() {
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Patient_name.getText().toString()==null){
                    Toast.makeText(getActivity(), "Please Enter Patient Name", Toast.LENGTH_SHORT).show();
                }else {
                    callserver();
                }
            }
        });
    }

    private void init() {
        Patient_name=view.findViewById(R.id.Patient_Name);
        Search=view.findViewById(R.id.Search_btn);
        Patient_Information=view.findViewById(R.id.Patients_List);
    }
}
