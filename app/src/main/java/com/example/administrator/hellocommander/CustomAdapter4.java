package com.example.administrator.hellocommander;

//전역자 테이블을 연결시키기 위한 커스텀 어댑터

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CustomAdapter4 extends ArrayAdapter<DeadArticle> {

    private Context context;
    private int layoutResourceTd;
    private ArrayList<DeadArticle> listdata;

    public CustomAdapter4(Context context, int layoutResourceTd, ArrayList<DeadArticle> listdata){
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

        TextView tvText1 = (TextView) row.findViewById(R.id.textView28);
        TextView tvText2 = (TextView) row.findViewById(R.id.textView27);
        TextView tvText3 = (TextView) row.findViewById(R.id.textView30);
        TextView tvText4 = (TextView) row.findViewById(R.id.textView29);

        tvText1.setText(listdata.get(position).getJR2());
        tvText2.setText(listdata.get(position).getName2());
        tvText3.setText(listdata.get(position).getGJ2());
        tvText4.setText(listdata.get(position).getOD2());

        ImageView imageView = (ImageView) row.findViewById(R.id.imageView3);

        try {
            if(listdata.get(position).getImg2().indexOf("def.png") != -1)
            {

                imageView.setImageResource(R.drawable.def);
            }
            else {

                File imgfile = new File(listdata.get(position).getImg2());
                Bitmap d = BitmapFactory.decodeFile(imgfile.getAbsolutePath());
                //imageView.setImageDrawable(d);
                imageView.setImageBitmap(d);
            }
        }catch (Exception e) {
            Log.e("Error", "Error : " + e);
        }
        return row;
        }
    }
