package com.example.administrator.hellocommander;

//알람 테이블 생성

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DaoAlarm {

    private Context context;
    private SQLiteDatabase database;

    public DaoAlarm(Context context) {
        this.context = context;

        database = context.openOrCreateDatabase("OSAM.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        try {
            String sql = "CREATE TABLE IF NOT EXISTS InkyuChoi_Alarm (LIST integer primary key autoincrement, "
                    + "Name3 text not null, ARf text not null, ARs text not null, Content text not null, fnum text not null, snum text not null);";

            database.execSQL(sql);
        } catch (Exception e) {
            Log.e("test", "Create FAILLL" + e);
            e.printStackTrace();
        }
    }

    public String InsertMain(String a1,String a2,String a3,String a4,String a5,String a6){
        String insertData1 = a1;
        String insertData2 = a2;
        String insertData3 = a3;
        String insertData4 = a4;
        String insertData5 = a5;
        String insertData6 = a6;
        String sql = "INSERT INTO InkyuChoi_Alarm(Name3, ARf, ARs, Content, fnum, snum) VALUES('"+insertData1+"','"+insertData2+"','"+insertData3+"','"+insertData4+"','"+insertData5+"','"+insertData6+"');";
        database.execSQL(sql);

        return null;
    }
    public ArrayList<AlarmArticle> getArticleList(String a){

        ArrayList<AlarmArticle> articlelist = new ArrayList<AlarmArticle>();

        String insert = a;

        String Name3;
        String ARf;
        String ARs;
        String Content;
        String fnum;
        String snum;

        String sql = "SELECT * FROM InkyuChoi_Alarm WHERE Name3= '"+insert+"' ORDER BY LIST DESC;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Name3 = cursor.getString(1);
            ARf = cursor.getString(2);
            ARs = cursor.getString(3);
            Content =cursor.getString(4);
            fnum=cursor.getString(5);
            snum =cursor.getString(6);

            articlelist.add(new AlarmArticle(Name3, ARf, ARs, Content, fnum, snum));
        }

        cursor.close();
        return articlelist;
    }
    public ArrayList<AlarmArticle> getArticleListpurely(){

        ArrayList<AlarmArticle> articlelist = new ArrayList<AlarmArticle>();

        String Name3;
        String ARf;
        String ARs;
        String Content;
        String fnum;
        String snum;

        String sql = "SELECT * FROM InkyuChoi_Alarm ORDER BY LIST DESC;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Name3 = cursor.getString(1);
            ARf = cursor.getString(2);
            ARs = cursor.getString(3);
            Content =cursor.getString(4);
            fnum=cursor.getString(5);
            snum =cursor.getString(6);

            articlelist.add(new AlarmArticle(Name3, ARf, ARs, Content, fnum, snum));
        }

        cursor.close();
        return articlelist;
    }

    public void settingfnumbyListnumber(String a, String b, String c, String d)
    {
        String InsetString = a;
        String insertData = b;
        String insertData2 = c;
        String insertData3 = d;
        String sql = "UPDATE InkyuChoi_Alarm SET fnum = '" + InsetString + "' WHERE ARf = '" + insertData + "' AND ARs = '" + insertData2 +  "' AND Content = '" + insertData3 + "';";
        database.execSQL(sql);
    }

    public void settingsnumbyListnumber(String a, String b, String c,String d)
    {
        String InsetString = a;
        String insertData = b;
        String insertData2 = c;
        String insertData3 = d;
        String sql = "UPDATE InkyuChoi_Alarm SET snum = '" + InsetString + "' WHERE ARf = '" + insertData + "' AND ARs = '" + insertData2 +  "' AND Content = '" + insertData3 + "';";
        database.execSQL(sql);
    }

    public String DeleteData(String d1)
    {
        String insertData = d1;

        String sql = "DELETE FROM InkyuChoi_Alarm WHERE Name3 = '" + insertData + "';";
        database.execSQL(sql);

        return null;
    }

}
