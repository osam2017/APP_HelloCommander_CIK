package com.example.administrator.hellocommander;

//전역자 테이블 생성

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DaoDead {

    private Context context;
    private SQLiteDatabase database;

    public DaoDead(Context context) {
        this.context = context;

        database = context.openOrCreateDatabase("OSAM.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        try {
            String sql = "CREATE TABLE IF NOT EXISTS InkyuChoi_Dead (LIST integer primary key autoincrement, "
                    + "img2 text not null, Name2 text not null, JR2 text not null, GJ2 text not null, OD2 text not null);";

            database.execSQL(sql);
        } catch (Exception e) {
            Log.e("test", "Create FAILLL" + e);
            e.printStackTrace();
        }
    }

    public String InsertDead(String a1,String a2,String a3,String a4,String a5){
        String insertData1 = a1;
        String insertData2 = a2;
        String insertData3 = a3;
        String insertData4 = a4;
        String insertData5 = a5;
        String sql = "INSERT INTO InkyuChoi_Dead(img2, Name2, JR2, GJ2, OD2) VALUES('"+insertData1+"','"+insertData2+"','"+insertData3+"','"+insertData4+"','"+insertData5+"');";
        database.execSQL(sql);

        return null;
    }

    public ArrayList<DeadArticle> getArticleList(){

        ArrayList<DeadArticle> articlelist = new ArrayList<DeadArticle>();

        String img2;
        String Name2;
        String JR2;
        String GJ2;
        String OD2;

        String sql = "SELECT * FROM InkyuChoi_Dead ORDER BY LIST DESC;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            img2 = cursor.getString(1);
            Name2 = cursor.getString(2);
            JR2 =cursor.getString(3);
            GJ2=cursor.getString(4);
            OD2 = cursor.getString(5);

            articlelist.add(new DeadArticle(img2, Name2, JR2, GJ2, OD2));
        }

        cursor.close();
        return articlelist;
    }
}
