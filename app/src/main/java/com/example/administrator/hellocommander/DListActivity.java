package com.example.administrator.hellocommander;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DListActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mainlist;
    private ArrayList<DeadArticle> articlelist;
    private Button but1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dlist);

        ImageButton imgbut1 = (ImageButton) findViewById(R.id.imageButton8);
        imgbut1.setOnClickListener(this);

        mainlist = (ListView) findViewById(R.id.deadlist);
        DaoDead dao = new DaoDead(getApplicationContext());

        articlelist = dao.getArticleList();

        CustomAdapter4 customAdapter4 = new CustomAdapter4(this, R.layout.deadlistlayout, articlelist);
        try {
            mainlist.setAdapter(customAdapter4);

        } catch (Exception e){
            Toast.makeText(getApplicationContext(),"error" + e,Toast.LENGTH_LONG).show();
            Log.e("test","error" + e);
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imageButton8:
                finish();
                break;
        }

    }
}
