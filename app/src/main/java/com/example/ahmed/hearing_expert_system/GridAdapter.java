package com.example.ahmed.hearing_expert_system;

import android.content.Context;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ahmed on 3/1/2018.
 */

public class GridAdapter extends BaseAdapter {

    private int icons[];
    private String letters[];
    private Context context;
    private LayoutInflater inflater;

    public GridAdapter(Context context, int icons[],String letters[]){

        this.context=context;
        this.icons=icons;
        this.letters=letters;

    }

    @Override
    public int getCount() {
        return letters.length;
    }

    @Override
    public Object getItem(int position) {
        return letters[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridview = convertView;
        if (convertView==null){
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridview=inflater.inflate(R.layout.custom_layout,null);

        }

        ImageView imageView = (ImageView)gridview.findViewById(R.id.icons);
        TextView textView = (TextView)gridview.findViewById(R.id.letters);

        imageView.setImageResource(icons[position]);
        textView.setText(letters[position]);

        return gridview;
    }
}
