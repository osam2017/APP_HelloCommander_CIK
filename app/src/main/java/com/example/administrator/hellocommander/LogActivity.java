package com.example.administrator.hellocommander;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class LogActivity extends AppCompatActivity implements OnClickListener {

    private ListView mainlist;
    private ArrayList<LogArticle> articlelist;
    private Button but1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        mainlist = (ListView) findViewById(R.id.loglist2);
        but1 = (Button) findViewById(R.id.button11);
        but1.setOnClickListener(this);

        DaoLog dao = new DaoLog(getApplicationContext());

        articlelist = dao.getArticleList2();

        CustomAdapter2 customAdapter2 = new CustomAdapter2(this, R.layout.loglist, articlelist);
        try {
            mainlist.setAdapter(customAdapter2);

        } catch (Exception e){
            Log.e("test","error" + e);
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button11:
                finish();
                break;
        }
    }
}
