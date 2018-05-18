package com.example.ahmed.hearingexpretsystem.model;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ahmed.hearingexpretsystem.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Ahmed on 5/17/2018.
 */

public class Patient_List_Adapter extends BaseAdapter {
    Activity activity;
    ArrayList<Patient_Data> patient_data;
    LayoutInflater inflater=null;

    public Patient_List_Adapter(Activity activity, ArrayList<Patient_Data> patients_data) {
        this.activity=activity;
        this.patient_data=patients_data;
        this.inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return patient_data.size();
    }

    @Override
    public Object getItem(int position) {
        return patient_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return patient_data.get(position).getP_ID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();
        View row=inflater.inflate(R.layout.patient_list_item,null);
        holder.P_name=row.findViewById(R.id.P_name);
        holder.P_age=row.findViewById(R.id.P_age);
        holder.Noise_type=row.findViewById(R.id.Noise_type);
        holder.Test_Degree=row.findViewById(R.id.Test_Degree);
        holder.Dr_name=row.findViewById(R.id.Dr_name);

        holder.P_name.setText(patient_data.get(position).getP_name());
        holder.P_age.setText(patient_data.get(position).getP_age());
        holder.Noise_type.setText(patient_data.get(position).getNoise_type());
        holder.Test_Degree.setText(patient_data.get(position).getTest_Degree());
        holder.Dr_name.setText(patient_data.get(position).getDr_name());

        return row;
    }
    public class Holder{
        TextView P_name;
        TextView P_age;
        TextView Noise_type;
        TextView Test_Degree;
        TextView Dr_name;
    }
}
