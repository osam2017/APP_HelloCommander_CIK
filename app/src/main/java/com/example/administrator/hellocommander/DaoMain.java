package com.example.administrator.hellocommander;

//용사(핵심정보) 테이블 생성

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DaoMain {
    private Context context;
    private SQLiteDatabase database;

    public DaoMain(Context context) {
        this.context = context;

        database = context.openOrCreateDatabase("OSAM.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        try {
            String sql = "CREATE TABLE IF NOT EXISTS InkyuChoi_Yongsa (LIST integer primary key autoincrement, "
                    + "img text not null, GG text not null, Name text not null, JR text not null, GJ text not null, BD text not null, ID text not null, OD text not null, NOW text not null);";

            database.execSQL(sql);
        } catch (Exception e) {
            Log.e("test", "Create FAILLL" + e);
            e.printStackTrace();
        }
    }

    //데이터에 새로운 값을 넣는 함수
    public String InsertMain(String a1,String a2,String a3,String a4,String a5,String a6,String a7,String a8,String a9){
        String insertData1 = a1;
        String insertData2 = a2;
        String insertData3 = a3;
        String insertData4 = a4;
        String insertData5 = a5;
        String insertData6 = a6;
        String insertData7 = a7;
        String insertData8 = a8;
        String insertData9 = a9;
        String sql = "INSERT INTO InkyuChoi_Yongsa(img, GG, Name, JR, GJ, BD, ID, OD, NOW) VALUES('"+insertData1+"','"+insertData2+"','"+insertData3+"','"+insertData4+"','"+insertData5+"','"+insertData6+"','"+insertData7+"','"+insertData8+"','"+insertData9+"');";
        database.execSQL(sql);

        return null;
    }

    //변수의 값에 따라 데이터를 삭제하는 함수들
    public String DeleteData(int d1) {
        int insertData = d1;

        String sql = "DELETE FROM InkyuChoi_Yongsa WHERE LIST = '" + insertData + "';";
        database.execSQL(sql);

        return null;
    }
    public String DeleteData(String d1) {
        String insertData = d1;

        String sql = "DELETE FROM InkyuChoi_Yongsa WHERE Name = '" + insertData + "';";
        database.execSQL(sql);

        return null;
    }

    //선택에 따른 데이터베이스 정렬 함수들
    public ArrayList<MainArticle> getArticleList(){

        ArrayList<MainArticle> articlelist = new ArrayList<MainArticle>();

         String img;
         String GG;
         String Name;

         String JR;
         String GJ;
         String BD;

         String ID;
         String OD;
         String NOW;

        String sql = "SELECT * FROM InkyuChoi_Yongsa ORDER BY LIST DESC;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            img = cursor.getString(1);
            GG = cursor.getString(2);
            Name = cursor.getString(3);

            JR =cursor.getString(4);
            GJ=cursor.getString(5);
            BD =cursor.getString(6);

            ID =cursor.getString(7);
            OD = cursor.getString(8);
            NOW= cursor.getString(9);

            articlelist.add(new MainArticle(img, GG, Name, JR, GJ, BD, ID, OD, NOW));
        }

        cursor.close();
        return articlelist;
    }
    public ArrayList<MainArticle> getArticleList0(){

        ArrayList<MainArticle> articlelist = new ArrayList<MainArticle>();

        String img;
        String GG;
        String Name;

        String JR;
        String GJ;
        String BD;

        String ID;
        String OD;
        String NOW;

        String sql = "SELECT * FROM InkyuChoi_Yongsa ORDER BY Name DESC;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            img = cursor.getString(1);
            GG = cursor.getString(2);
            Name = cursor.getString(3);

            JR =cursor.getString(4);
            GJ=cursor.getString(5);
            BD =cursor.getString(6);

            ID =cursor.getString(7);
            OD = cursor.getString(8);
            NOW= cursor.getString(9);

            articlelist.add(new MainArticle(img, GG, Name, JR, GJ, BD, ID, OD, NOW));
        }

        cursor.close();
        return articlelist;
    }
    public ArrayList<MainArticle> getArticleList1(){

        ArrayList<MainArticle> articlelist = new ArrayList<MainArticle>();

        String img;
        String GG;
        String Name;

        String JR;
        String GJ;
        String BD;

        String ID;
        String OD;
        String NOW;

        String sql = "SELECT * FROM InkyuChoi_Yongsa ORDER BY Name ASC;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            img = cursor.getString(1);
            GG = cursor.getString(2);
            Name = cursor.getString(3);

            JR =cursor.getString(4);
            GJ=cursor.getString(5);
            BD =cursor.getString(6);

            ID =cursor.getString(7);
            OD = cursor.getString(8);
            NOW= cursor.getString(9);

            articlelist.add(new MainArticle(img, GG, Name, JR, GJ, BD, ID, OD, NOW));
        }

        cursor.close();
        return articlelist;
    }
    public ArrayList<MainArticle> getArticleList2(){

        ArrayList<MainArticle> articlelist = new ArrayList<MainArticle>();

        String img;
        String GG;
        String Name;

        String JR;
        String GJ;
        String BD;

        String ID;
        String OD;
        String NOW;

        String sql = "SELECT * FROM InkyuChoi_Yongsa ORDER BY GG ASC;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            img = cursor.getString(1);
            GG = cursor.getString(2);
            Name = cursor.getString(3);

            JR =cursor.getString(4);
            GJ=cursor.getString(5);
            BD =cursor.getString(6);

            ID =cursor.getString(7);
            OD = cursor.getString(8);
            NOW= cursor.getString(9);

            articlelist.add(new MainArticle(img, GG, Name, JR, GJ, BD, ID, OD, NOW));
        }

        cursor.close();
        return articlelist;
    }
    public ArrayList<MainArticle> getArticleList3(){

        ArrayList<MainArticle> articlelist = new ArrayList<MainArticle>();

        String img;
        String GG;
        String Name;

        String JR;
        String GJ;
        String BD;

        String ID;
        String OD;
        String NOW;

        String sql = "SELECT * FROM InkyuChoi_Yongsa ORDER BY JR DESC;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            img = cursor.getString(1);
            GG = cursor.getString(2);
            Name = cursor.getString(3);

            JR =cursor.getString(4);
            GJ=cursor.getString(5);
            BD =cursor.getString(6);

            ID =cursor.getString(7);
            OD = cursor.getString(8);
            NOW= cursor.getString(9);

            articlelist.add(new MainArticle(img, GG, Name, JR, GJ, BD, ID, OD, NOW));
        }

        cursor.close();
        return articlelist;
    }
    public ArrayList<MainArticle> getArticleList4(){

        ArrayList<MainArticle> articlelist = new ArrayList<MainArticle>();

        String img;
        String GG;
        String Name;

        String JR;
        String GJ;
        String BD;

        String ID;
        String OD;
        String NOW;

        String sql = "SELECT * FROM InkyuChoi_Yongsa ORDER BY NOW DESC;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            img = cursor.getString(1);
            GG = cursor.getString(2);
            Name = cursor.getString(3);

            JR =cursor.getString(4);
            GJ=cursor.getString(5);
            BD =cursor.getString(6);

            ID =cursor.getString(7);
            OD = cursor.getString(8);
            NOW= cursor.getString(9);

            articlelist.add(new MainArticle(img, GG, Name, JR, GJ, BD, ID, OD, NOW));
        }

        cursor.close();
        return articlelist;
    }

    //테스트 데이터를 입력하는 함수들
    public String getJsonTestData() {

        StringBuilder sb = new StringBuilder();
        sb.append("");

        sb.append("[");

        sb.append("      {");
        sb.append("         'img':'a1.jpg',");
        sb.append("         'GG':'오늘도 좋은 하루1',");
        sb.append("         'Name':'학생1',");
        sb.append("         'JR':'예스',");
        sb.append("         'GJ':'예스2',");
        sb.append("         'BD':'2013-09-23-10-10',");
        sb.append("         'ID':'2013-09-23-10-10',");
        sb.append("         'OD':'2013-09-23-10-10',");
        sb.append("         'NOW':'IN'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'img':'a2.jpg',");
        sb.append("         'GG':'오늘도 좋은 하루2',");
        sb.append("         'Name':'학생2',");
        sb.append("         'JR':'예스',");
        sb.append("         'GJ':'예스2',");
        sb.append("         'BD':'2013-09-23-10-10',");
        sb.append("         'ID':'2013-09-23-10-10',");
        sb.append("         'OD':'2013-09-23-10-10',");
        sb.append("         'NOW':'OUT'");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'img':'a3.jpg',");
        sb.append("         'GG':'오늘도 좋은 하루3',");
        sb.append("         'Name':'학생3',");
        sb.append("         'JR':'예스',");
        sb.append("         'GJ':'예스2',");
        sb.append("         'BD':'2013-09-23-10-10',");
        sb.append("         'ID':'2013-09-23-10-10',");
        sb.append("         'OD':'2013-09-23-10-10',");
        sb.append("         'NOW':'IN'");
        sb.append("      }");

        sb.append("]");

        return sb.toString();
    }
    public void insertJsonData(String JsonData){

        String img;
        String GG;
        String Name;

        String JR;
        String GJ;
        String BD;

        String ID;
        String OD;
        String NOW;

        try{
            JSONArray jArr = new JSONArray(JsonData);

            for(int i=0; i< jArr.length(); ++i){
                JSONObject jobj = jArr.getJSONObject(i);

                img = jobj.getString("img");
                GG = jobj.getString("GG");
                Name = jobj.getString("Name");
                JR = jobj.getString("JR");
                GJ = jobj.getString("GJ");
                BD = jobj.getString("BD");
                ID = jobj.getString("ID");
                OD = jobj.getString("OD");
                NOW = jobj.getString("NOW");

                String sql = "INSERT INTO InkyuChoi_Yongsa(img, GG, Name, JR, GJ, BD, ID, OD, NOW)"
                        + " VALUES('" + img + "','" + GG + "','" + Name + "','" + JR + "','" + GJ + "','" + BD + "','"
                        + ID + "','" + OD + "','" + NOW +"');";

                try {
                    database.execSQL(sql);
                }
                catch (Exception e)
                {
                    Log.e("test3","ERRORORO " + e);
                    e.printStackTrace();
                }

            }

        }catch (JSONException e){
            Log.e("test2","JSON ERRORORO " + e);
            e.printStackTrace();
        }

    }

    //특정 조건의 함수를 구하는 함수들
    public MainArticle getArticleByBirthDay(String BD){

        MainArticle article = null;

        String img;
        String GG;
        String Name;

        String JR;
        String GJ;
        //String BD;

        String ID;
        String OD;
        String NOW;

        String sql = "SELECT * FROM InkyuChoi_Yongsa WHERE BD = '" + BD + "';";
        Cursor cursor = database.rawQuery(sql, null);

        cursor.moveToNext();
            img = cursor.getString(1);
            GG = cursor.getString(2);
            Name = cursor.getString(3);

            JR =cursor.getString(4);
            GJ=cursor.getString(5);
            BD =cursor.getString(6);

            ID =cursor.getString(7);
            OD = cursor.getString(8);
            NOW= cursor.getString(9);

            article = new MainArticle(img, GG, Name, JR, GJ, BD, ID, OD, NOW);


        cursor.close();
        return article;
    }
    public MainArticle getArticleLatelyODMan(){

        MainArticle article = null;

        String img;
        String GG;
        String Name;

        String JR;
        String GJ;
        String BD;

        String ID;
        String OD;
        String NOW;

        String sql = "SELECT * FROM InkyuChoi_Yongsa ORDER BY OD ASC ;";
        Cursor cursor = database.rawQuery(sql, null);

        cursor.moveToNext();
        img = cursor.getString(1);
        GG = cursor.getString(2);
        Name = cursor.getString(3);

        JR =cursor.getString(4);
        GJ=cursor.getString(5);
        BD =cursor.getString(6);

        ID =cursor.getString(7);
        OD = cursor.getString(8);
        NOW= cursor.getString(9);

        article = new MainArticle(img, GG, Name, JR, GJ, BD, ID, OD, NOW);


        cursor.close();
        return article;
    }

    //특정 조건의 데이터의 값을 바꾸는 함수들
    public void setINOUTByName(String Name, String NOW) {
        String insertData1 = Name;
        String insertData2 = NOW;

        String sql = "UPDATE InkyuChoi_Yongsa SET NOW = '" + insertData2 + "' WHERE Name = '" + insertData1 + "';";
        database.execSQL(sql);
    }
    public void setGGByName(String Name, String GG) {
        String insertData1 = Name;
        String insertData2 = GG;

        String sql = "UPDATE InkyuChoi_Yongsa SET GG = '" + insertData2 + "' WHERE Name = '" + insertData1 + "';";
        database.execSQL(sql);
    }
}
