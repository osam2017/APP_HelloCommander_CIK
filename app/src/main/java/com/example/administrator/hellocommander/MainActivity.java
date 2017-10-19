package com.example.administrator.hellocommander;

/*
 ┌---------------------------------------------------┐
    - 오픈 소스 아카데미 프로젝트 : Hello Commander

    - 국직부대 공군 병장 최인규 제작

    - 제작 기간 : 2017-10-16 ~ 2017-10-19
 └---------------------------------------------------┘
 */

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

public class MainActivity extends AppCompatActivity implements OnClickListener {

        private ListView mainlist;
        private ArrayList<LogArticle> articlelist;

    @Override
    protected void onResume()
    {
        // TODO Auto-generated method stub
        super.onResume();
        mainlist = (ListView) findViewById(R.id.mainlist);

        DaoLog dao = new DaoLog(getApplicationContext());
        articlelist = dao.getArticleList();

        CustomAdapter2 customAdapter2 = new CustomAdapter2(this, R.layout.loglist, articlelist);
        mainlist.setAdapter(customAdapter2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button but1 = (Button) findViewById(R.id.button7);
        but1.setOnClickListener(this);


        mainlist = (ListView) findViewById(R.id.mainlist);
        DaoLog dao = new DaoLog(getApplicationContext());
        articlelist = dao.getArticleList();

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
        switch(v.getId()){
            case R.id.button7:
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                break;

        }
    }

    public void linearOnClick(View v){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
