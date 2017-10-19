package com.example.administrator.hellocommander;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.DialogInterface;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import android.util.Log;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgv1;

    private TextView txtv6;
    private TextView txtv5;
    private TextView txtv4;

    private TextView txtv8;
    private TextView txtv7;
    private TextView txtv9;
    private TextView txtv10;

    private ImageButton imgbut3;
    private ImageButton imgbut4;
    private ImageButton imgbut5;

    private Button but3;
    private Button but5;

    private DaoAlarm daoa;
    private DaoMain daom;
    private DaoHoliday daoh;
    private DaoLog daol;

    private MainArticle article;

    private ListView chullist;
    private ListView detailist;

    private ArrayList<AlarmArticle> articlelist;
    private ArrayList<HolidayArcticle> articlelist2;

    private String deletingName;
    private String BDD;
    private String CHG;

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        refresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        imgv1 = (ImageView) findViewById(R.id.imageView2);

        txtv6 = (TextView) findViewById(R.id.textView6);
        txtv5 = (TextView) findViewById(R.id.textView5);
        txtv4 = (TextView) findViewById(R.id.textView4);

        txtv8 = (TextView) findViewById(R.id.textView8);
        txtv7 = (TextView) findViewById(R.id.textView7);
        txtv9 = (TextView) findViewById(R.id.textView9);
        txtv10 = (TextView) findViewById(R.id.textView10);

        imgbut3 = (ImageButton) findViewById(R.id.imageButton3);
        imgbut3.setOnClickListener(this);

        imgbut4 = (ImageButton) findViewById(R.id.imageButton4);
        imgbut4.setOnClickListener(this);

        imgbut5 = (ImageButton) findViewById(R.id.imageButton5);
        imgbut5.setOnClickListener(this);

        but3 = (Button) findViewById(R.id.button3);
        but3.setOnClickListener(this);

        but5 = (Button) findViewById(R.id.button5);
        but5.setOnClickListener(this);

        chullist = (ListView) findViewById(R.id.chullist);
        detailist =(ListView) findViewById(R.id.detaillist);

        daoa = new DaoAlarm(getApplicationContext());
        daoh = new DaoHoliday(getApplicationContext());
        daom = new DaoMain(getApplicationContext());
        daol = new DaoLog(getApplicationContext());

        BDD = getIntent().getExtras().getString("BD");

        refresh();

        try {
            if(article.getImg().indexOf("def.png") != -1)
            {
                imgv1.setImageResource(R.drawable.def);
            }
            else {

                File imgfile = new File(article.getImg());
                Bitmap d = BitmapFactory.decodeFile(imgfile.getAbsolutePath());
                imgv1.setImageBitmap(d);
            }
        }catch (Exception e){
            Log.e("imgerror","Error:" + e);
        }

    }
    //예,아니오 다이얼로그 함수
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    String NewGG = "";
                    int a = 0;
                    switch (article.getName())
                    {
                        case "이병":
                            NewGG = "일병";
                            break;
                        case "일병":
                            NewGG = "상병";
                            break;
                        case "상병":
                            NewGG = "병장";
                            break;
                        case "병장":
                            a = 1;
                            break;

                    }

                    try {
                        if(a == 0) {
                            daom.setGGByName(deletingName, NewGG);
                        }else if(a == 1){
                            Toast.makeText(getApplicationContext(),"더이상 진급할 수 없습니다.",Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Log.e("test", "error" + e);
                        e.printStackTrace();
                    }

                    refresh();
                    break;
            }
        }
    };

    //버튼 클릭 함수
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton3:
                finish();
                break;
            case R.id.imageButton4:
                showAlertDialog();
                break;
            case R.id.imageButton5:
                showAlertDialog2();
                break;
            case R.id.button3:
                deletingName = article.getGG();

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("진급");
                builder.setMessage("이 병사를 진급시키겠습니까?");
                builder.setNegativeButton("예", dialogClickListener);
                builder.setPositiveButton("아니오", dialogClickListener);

                builder.show();
                break;
            case R.id.button5:
                Intent intent = new Intent(this, PListActivity.class);
                intent.putExtra("Change","1");
                intent.putExtra("BD", article.getBD() + "");
                startActivity(intent);
        }

    }

    //목록 갱신화
    public void refresh() {
        article = daom.getArticleByBirthDay(BDD);

        txtv6.setText(article.getName());
        txtv5.setText(article.getGG());
        txtv4.setText(article.getGJ());

        txtv8.setText(article.getJR());
        txtv7.setText("생일 : " + article.getBD());
        txtv9.setText("입대일 : " + article.getID());
        txtv10.setText("전역일 : " + article.getOD());

        articlelist = daoa.getArticleList(article.getGG());
        CustomAdapter5 customAdapter5 = new CustomAdapter5(this, R.layout.calendarlayout, articlelist);
        chullist.setAdapter(customAdapter5);

        articlelist2 = daoh.getArticleList(article.getGG());
        CustomAdapter6 customAdapter6 = new CustomAdapter6(this, R.layout.calendarlayout, articlelist2);
        detailist.setAdapter(customAdapter6);
    }

    //일정 추가 창
    private void showAlertDialog(){
        LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout dateLayout = (LinearLayout)vi.inflate(R.layout.alarmalert, null);

        final EditText ct = (EditText) dateLayout.findViewById(R.id.content);
        final DatePicker dp = (DatePicker) dateLayout.findViewById(R.id.datePicker);
        final Spinner sp = (Spinner) dateLayout.findViewById(R.id.spinner4);

        ArrayAdapter GGAdapter = ArrayAdapter.createFromResource(this, R.array.Dday, android.R.layout.simple_spinner_item);
        GGAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(GGAdapter);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        adb.setTitle("새 일정 추가");
        adb.setView(dateLayout);
        adb.setNeutralButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                daoa.InsertMain(article.getGG(), underZero(dp.getYear()) +" / " + underZero(dp.getMonth()+1) + " / " + underZero(dp.getDayOfMonth()), minusday(dp.getYear(),dp.getMonth()+1,dp.getDayOfMonth(),Integer.parseInt(sp.getSelectedItem().toString())),ct.getText().toString(), "0","0");

                Toast.makeText(getApplicationContext(),"일정 추가 완료", Toast.LENGTH_SHORT).show();
                refresh();
            }
        }).show();
    }
    //휴가 추가 창
    private void showAlertDialog2(){
        LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout dateLayout = (LinearLayout)vi.inflate(R.layout.holidayalert, null);

        final DatePicker dp1 = (DatePicker) dateLayout.findViewById(R.id.datePicker2);
        final DatePicker dp2 = (DatePicker) dateLayout.findViewById(R.id.datePicker3);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        adb.setTitle("새 휴가 추가");
        adb.setView(dateLayout);
        adb.setNeutralButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dp1.getYear()*10000 + dp1.getMonth()*100 + dp1.getDayOfMonth() > dp2.getYear()*10000 + dp2.getMonth()*100 + dp2.getDayOfMonth()){
                    Toast.makeText(getApplicationContext(),"출발 날짜가 도착 날짜를 앞설수 없습니다!", Toast.LENGTH_LONG).show();
                }
                else {
                    daoh.InsertMain(article.getGG(), underZero(dp1.getYear()) +" / " + underZero(dp1.getMonth()+1) + " / " + underZero(dp1.getDayOfMonth()),underZero(dp2.getYear()) +" / " + underZero(dp2.getMonth()+1) + " / " + underZero(dp2.getDayOfMonth()),sameimi(dp1.getYear(),dp1.getMonth(),dp1.getDayOfMonth(),dp2.getYear(),dp2.getMonth(),dp2.getDayOfMonth()),"0","0");

                    Toast.makeText(getApplicationContext(),"휴가 추가 완료", Toast.LENGTH_SHORT).show();
                    refresh();
                }

            }
        }).show();
    }

    //정해진 날짜 양식에 정해진 숫자만큼 날짜를 빼는 함수
    public String minusday(int a1, int a2, int a3, int b) {
        int Year = a1;
        int Month = a2;
        int Day = a3;

        int plusDay = 0;
    if (Day > b) {
        Day = Day - b;
    } else {
        Month = Month - 1;
        switch (Month) {
            case 1:
                plusDay = 31;
                break;
            case 2:
                if (Year % 4 == 0) {
                    plusDay = 29;
                } else {
                    plusDay = 28;
                }
                break;
            case 3:
                plusDay = 31;
                break;
            case 4:
                plusDay = 30;
                break;
            case 5:
                plusDay = 31;
                break;
            case 6:
                plusDay = 30;
                break;
            case 7:
                plusDay = 31;
                break;
            case 8:
                plusDay = 31;
                break;
            case 9:
                plusDay = 30;
                break;
            case 10:
                plusDay = 31;
                break;
            case 11:
                plusDay = 30;
                break;
            case 0:
                plusDay = 31;
                Year = Year - 1;
                Month = 12;
                break;

        }
        Day = Day + plusDay - b;
    }

        String years = String.valueOf(Year);
        String months;
        if(Month < 10) {
            months = "0" + String.valueOf(Month);
        }
        else
        {
            months = String.valueOf(Month);
        }
        String Days;
        if(Day < 10) {
            Days = "0" + String.valueOf(Day);
        }
        else
        {
            Days = String.valueOf(Day);
        }

        return years + " / " + months + " / " + Days;


    }
    //숫자가 10 미만일떄 앞에 0을 붙여주는 함수
    public String underZero(int a) {
        if(a < 10){
            return "0" + a;
        }
        else
        {
            return String.valueOf(a);
        }
    }
    //같은 값인가 확인하는 함수
    public String sameimi(int a, int b, int c, int d, int e, int f){
        if(a == d && b == e && c == f){
            return "외출";
        }
        else
        {
            return "휴가 및 외박";
        }
    }
}
