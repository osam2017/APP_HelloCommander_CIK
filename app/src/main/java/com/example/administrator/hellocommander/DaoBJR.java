package com.example.administrator.hellocommander;

//종류 테이블 생성

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DaoBJR {

    private Context context;
    private SQLiteDatabase database;

    public DaoBJR(Context context) {
        this.context = context;

        database = context.openOrCreateDatabase("OSAM.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        try {
            String sql = "CREATE TABLE IF NOT EXISTS InkyuChoi_JR(AdminJR text not null);";
            database.execSQL(sql);
        } catch (Exception e) {
            Log.e("test", "Create FAILLL" + e);
            e.printStackTrace();
        }
    }


        public void insertJsonData(String jsondata){
        String AdminJR;

        try{
            JSONArray jarr = new JSONArray(jsondata);
            for(int i =0; i < jarr.length(); ++i){
                JSONObject jobj = jarr.getJSONObject(i);

                AdminJR = jobj.getString("AdminJR");

                String sql = "INSERT INTO InkyuChoi_JR(AdminJR)"
                        + "VALUES('" + AdminJR + "');";

                try {
                    database.execSQL(sql);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

        }catch (JSONException e){
            Log.e("test","JSON ERROR" + e);
            e.printStackTrace();
        }
    }
    public ArrayList<BJRArticle> getBJRArticleList(){

        ArrayList<BJRArticle> articleList = new ArrayList<BJRArticle>();

        String JR;

        String sql = "SELECT * FROM InkyuChoi_JR;";
        Cursor cursor = database.rawQuery(sql, null);

        while(cursor.moveToNext()){
            JR = cursor.getString(1);
        }
        cursor.close();
        return null;
    }

    public BJRArticle getBJRArticleListbynum(String articlenum){

        BJRArticle article = null;

        String JR;

        String sql = "SELECT * FROM InkyuChoi_JR WHERE AdminJR = 'youare';";
        Cursor cursor = database.rawQuery(sql, null);

        cursor.moveToNext();

            JR = cursor.getString(0);
            article = new BJRArticle(JR);

        cursor.close();
        return article;
    }

    public String InsertDataDB(String dt){
        String insertData = dt;
        String sql = "INSERT INTO InkyuChoi_JR(AdminJR) VALUES('"+insertData+"');";
        database.execSQL(sql);

        return null;
    }
    public String getJsonTestData() {

        StringBuilder sb = new StringBuilder();
        sb.append("");

        sb.append("[");

        sb.append("      {");
        sb.append("         'AdminJR':'2ban'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'AdminJR':'3ban'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'AdminJR':'1ban'");
        sb.append("      }");

        sb.append("]");

        return sb.toString();
    }


}
