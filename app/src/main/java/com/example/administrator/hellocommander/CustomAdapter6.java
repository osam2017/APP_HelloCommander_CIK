package com.example.administrator.hellocommander;

//휴가 테이블을 연결시키기 위한 커스텀 어댑터

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter6 extends ArrayAdapter<HolidayArcticle> {

    private Context context;
    private int layoutResourceTd;
    private ArrayList<HolidayArcticle> listdata;

    public CustomAdapter6(Context context, int layoutResourceTd, ArrayList<HolidayArcticle> listdata){
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

        TextView tvText1 = (TextView) row.findViewById(R.id.textView33);
        TextView tvText2 = (TextView) row.findViewById(R.id.textView32);
        TextView tvText3 = (TextView) row.findViewById(R.id.textView31);


        tvText1.setText(listdata.get(position).getARf2());
        tvText2.setText(listdata.get(position).getARs2());
        tvText3.setText(listdata.get(position).getContent2());

        return row;
        }
    }
