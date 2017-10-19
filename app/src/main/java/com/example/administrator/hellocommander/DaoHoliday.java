package com.example.administrator.hellocommander;

//휴가 테이블 생성

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DaoHoliday {

    private Context context;
    private SQLiteDatabase database;

    public DaoHoliday(Context context) {
        this.context = context;

        database = context.openOrCreateDatabase("OSAM.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        try {
            String sql = "CREATE TABLE IF NOT EXISTS InkyuChoi_Holiday (LIST integer primary key autoincrement, "
                    + "Name4 text not null, ARf2 text not null, ARs2 text not null, Content2 text not null, fnum2 text not null, snum2 text not null);";

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
        String sql = "INSERT INTO InkyuChoi_Holiday(Name4, ARf2, ARs2, Content2, fnum2, snum2) VALUES('"+insertData1+"','"+insertData2+"','"+insertData3+"','"+insertData4+"','"+insertData5+"','"+insertData6+"');";
        database.execSQL(sql);

        return null;
    }
    public ArrayList<HolidayArcticle> getArticleList(String a){

        ArrayList<HolidayArcticle> articlelist = new ArrayList<HolidayArcticle>();

        String insert = a;

        String Name3;
        String ARf;
        String ARs;
        String Content;
        String fnum;
        String snum;

        String sql = "SELECT * FROM InkyuChoi_Holiday WHERE Name4 = '"+insert+"' ORDER BY LIST DESC;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Name3 = cursor.getString(1);
            ARf = cursor.getString(2);
            ARs = cursor.getString(3);
            Content =cursor.getString(4);
            fnum=cursor.getString(5);
            snum =cursor.getString(6);

            articlelist.add(new HolidayArcticle(Name3, ARf, ARs, Content, fnum, snum));
        }

        cursor.close();
        return articlelist;
    }

    public ArrayList<HolidayArcticle> getArticleListPure() {
        ArrayList<HolidayArcticle> articlelist = new ArrayList<HolidayArcticle>();

        String Name3;
        String ARf;
        String ARs;
        String Content;
        String fnum;
        String snum;

        String sql = "SELECT * FROM InkyuChoi_Holiday;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Name3 = cursor.getString(1);
            ARf = cursor.getString(2);
            ARs = cursor.getString(3);
            Content =cursor.getString(4);
            fnum=cursor.getString(5);
            snum =cursor.getString(6);

            articlelist.add(new HolidayArcticle(Name3, ARf, ARs, Content, fnum, snum));
        }

        cursor.close();
        return articlelist;
    }
    public void settingfnumbyListnumber(String a, String b, String c)
    {
        String InsetString = a;
        String insertData = b;
        String insertData2 = c;

        String sql = "UPDATE InkyuChoi_Holiday SET fnum2 = '" + InsetString + "' WHERE ARf2 = '" + insertData + "' AND ARs2 = '" + insertData2 + "';";
        database.execSQL(sql);
    }

    public void settingsnumbyListnumber(String a, String b, String c)
    {
        String InsetString = a;
        String insertData = b;
        String insertData2 = c;

        String sql = "UPDATE InkyuChoi_Holiday SET snum2 = '" + InsetString + "' WHERE ARf2 = '" + insertData + "' AND ARs2 = '" + insertData2 + "';";
        database.execSQL(sql);
    }

    public String DeleteData(String d1)
    {
        String insertData = d1;

        String sql = "DELETE FROM InkyuChoi_Holiday WHERE Name4 = '" + insertData + "';";
        database.execSQL(sql);

        return null;
    }
}
