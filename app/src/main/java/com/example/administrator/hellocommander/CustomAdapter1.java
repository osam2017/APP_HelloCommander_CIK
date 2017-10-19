package com.example.administrator.hellocommander;

//리스트 데이터를 연결시키기 위한 커스텀 어댑터

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CustomAdapter1 extends ArrayAdapter<ListData1> {

    private Context context;
    private int layoutResourceTd;
    private ArrayList<ListData1> listdata;

    public CustomAdapter1(Context context, int layoutResourceTd, ArrayList<ListData1> listdata){
        super(context, layoutResourceTd, listdata);
        this.context = context;
        this.layoutResourceTd = layoutResourceTd;
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if(row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceTd,parent,false);
        }

        TextView tvText1 = (TextView) row.findViewById(R.id.style1textview1);
        TextView tvText2 = (TextView) row.findViewById(R.id.style1textview2);
        TextView tvText3 = (TextView) row.findViewById(R.id.style1textview3);
        TextView tvText4 = (TextView) row.findViewById(R.id.style1textview4);

        tvText1.setText(listdata.get(position).getTextName());
        tvText2.setText(listdata.get(position).getTextgyugup());
        tvText3.setText(listdata.get(position).getTextjongryu());
        tvText4.setText(listdata.get(position).getTextnow());

        ImageView imageView = (ImageView) row.findViewById(R.id.style1imageView1);

        try {
            InputStream is = context.getAssets().open(listdata.get(position).getImgName());
            Drawable d = Drawable.createFromStream(is, null);

            imageView.setImageDrawable(d);
        }catch (IOException e) {
            Log.e("Error", "Error : " + e);
        }
        return row;
        }
    }
