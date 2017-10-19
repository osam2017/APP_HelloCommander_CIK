package com.example.administrator.hellocommander;

//로그 테이블을 연결시키기 위한 커스텀 어댑터

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

public class CustomAdapter2 extends ArrayAdapter<LogArticle>{
    private Context context;
    private int layoutResourceTd;
    private ArrayList<LogArticle> listdata;

    public CustomAdapter2(Context context, int layoutResourceTd, ArrayList<LogArticle> listdata){
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

        TextView tvText1 = (TextView) row.findViewById(R.id.style2text);

        tvText1.setText(listdata.get(position).getLog());

        return row;

    }
}
