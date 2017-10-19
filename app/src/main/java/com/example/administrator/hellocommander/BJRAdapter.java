package com.example.administrator.hellocommander;

//종류 테이블을 연결시키기 위한 커스텀 어댑터
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


public class BJRAdapter extends ArrayAdapter<BJRArticle> {
    private Context context;
    private int layoutResourceTd;
    private ArrayList<BJRArticle> listdata;

    public BJRAdapter(Context context, int layoutResourceTd, ArrayList<BJRArticle> listdata){
        super(context, layoutResourceTd, listdata);
        this.context = context;
        this.layoutResourceTd = layoutResourceTd;
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
