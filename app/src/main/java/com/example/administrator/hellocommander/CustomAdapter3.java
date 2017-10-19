package com.example.administrator.hellocommander;

//병사 테이블을 연결시키기 위한 커스텀 어댑터

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
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CustomAdapter3 extends ArrayAdapter<MainArticle> {

    private Context context;
    private int layoutResourceTd;
    private ArrayList<MainArticle> listdata;

    public CustomAdapter3(Context context, int layoutResourceTd, ArrayList<MainArticle> listdata){
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

        tvText1.setText(listdata.get(position).getName());
        tvText2.setText(listdata.get(position).getGG());
        tvText3.setText(listdata.get(position).getGJ());
        tvText4.setText(listdata.get(position).getNOW());

        ImageView imageView = (ImageView) row.findViewById(R.id.style1imageView1);

        try {
            //InputStream is = context.getAssets().open(listdata.get(position).getImg());
            //Drawable d = Drawable.createFromStream(is, null);

            //final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            //final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

            if(listdata.get(position).getImg().indexOf("def.png") != -1)
            {

                imageView.setImageResource(R.drawable.def);
            }
            else {

                File imgfile = new File(listdata.get(position).getImg());
                Bitmap d = BitmapFactory.decodeFile(imgfile.getAbsolutePath());
                //imageView.setImageDrawable(d);
                imageView.setImageBitmap(d);
            }
        }catch (Exception e) {
            Log.e("Errorrrr", "Error : " + e);
        }
        return row;
        }
    }
