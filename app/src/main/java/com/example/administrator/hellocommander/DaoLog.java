package com.example.administrator.hellocommander;

//로그 테이블 생성

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-10-17.
 */

public class DaoLog {
    private Context context;
    private SQLiteDatabase database;

    public DaoLog(Context context){
        this.context = context;

        database = context.openOrCreateDatabase("OSAM.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        try {
            String sql = "CREATE TABLE IF NOT EXISTS InkyuChoi_Articles (ID integer primary key autoincrement, Log text not null);";
            database.execSQL(sql);
        } catch (Exception e) {
            Log.e("test", "Create FAILLL" + e);
            e.printStackTrace();
        }

    }

    public String InsertDataDB(String dt){
        String insertData = dt;
        String sql = "INSERT INTO InkyuChoi_Articles(Log) VALUES('"+insertData+"');";
        database.execSQL(sql);

        return null;
    }

    public ArrayList<LogArticle> getArticleList(){

        ArrayList<LogArticle> articlelist = new ArrayList<LogArticle>();

        String Log;

        String sql = "SELECT * FROM InkyuChoi_Articles ORDER BY ID DESC LIMIT 5;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Log = cursor.getString(1);
            articlelist.add(new LogArticle(Log));
        }

        cursor.close();
        return articlelist;
    }

    public ArrayList<LogArticle> getArticleList2(){

        ArrayList<LogArticle> articlelist = new ArrayList<LogArticle>();

        String Log;

        String sql = "SELECT * FROM InkyuChoi_Articles ORDER BY ID DESC";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Log = cursor.getString(1);
            articlelist.add(new LogArticle(Log));
        }

        cursor.close();
        return articlelist;
    }

    public String getJsonTestData() {

        StringBuilder sb = new StringBuilder();
        sb.append("");

        sb.append("[");

        sb.append("      {");
        sb.append("         'Log':'1'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'Log':'2'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'Log':'3'");
        sb.append("      }");

        sb.append("]");

        return sb.toString();
    }

    public void insertJsonData(String jsondata){
        String Log;

        try{
            JSONArray jarr = new JSONArray(jsondata);
            for(int i =0; i < jarr.length(); ++i){
                JSONObject jobj = jarr.getJSONObject(i);

                Log = jobj.getString("Log");

                String sql = "INSERT INTO InkyuChoi_Articles(Log)"
                        + "VALUES('" + Log + "');";

                try {
                    database.execSQL(sql);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
