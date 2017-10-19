package com.example.administrator.hellocommander;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.content.DialogInterface;
import java.util.ArrayList;
import android.support.v7.app.AlertDialog;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.app.PendingIntent;

import static java.security.AccessController.getContext;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, OnClickListener, AdapterView.OnItemSelectedListener {

    private int deleteNum = 0;
    private ImageButton imgbut3;
    private TextView textView1;

    private ArrayList<MainArticle> articlelist;
    private ArrayList<HolidayArcticle> checkarticleh;

    private ListView listview;
    private Spinner jungryulSp;

    private DaoMain daom;
    private CustomAdapter3 customAdapter3;
    private DaoLog dao;
    private DaoDead daod;
    private DaoHoliday daoh;
    private DaoAlarm daoa;


    private int deleting = 0;
    private String deletingstr = "";

    private String Alramall ="";


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        refresh();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listview = (ListView) findViewById(R.id.listlist);
        listview.setOnItemClickListener(this);

        Button but1 = (Button) findViewById(R.id.button);
        but1.setOnClickListener(this);
        Button but2 = (Button) findViewById(R.id.button2);
        but2.setOnClickListener(this);

        ImageButton imgbut0 = (ImageButton) findViewById(R.id.imageButton);
        imgbut0.setOnClickListener(this);

        imgbut3 = (ImageButton) findViewById(R.id.imageButton2);
        imgbut3.setOnClickListener(this);

        textView1 = (TextView) findViewById(R.id.textView);
        textView1.setText("");

        //dao 클래스들의 Context 초기화
        daom = new DaoMain(getApplicationContext());
        dao = new DaoLog(getApplicationContext());
        daoh = new DaoHoliday(getApplicationContext());
        daoa = new DaoAlarm(getApplicationContext());

        refresh();

        int alarmed = 0;

        if( articlelist.size() == 0){
            Alramall = "새로운 병사를 추가하세요!";
            alarmed = 1;
        }

        if(doesUserHavePermission() == false)
        {
            Toast.makeText(getApplicationContext(), "스토리지 권한을 허용으로 바꿔주세요!",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        //------------------------------------------------------------------------------------------------------------------------------------전체 데이터를 자동으로 검사하는 과정

        try {

            MainArticle voidarticle = daom.getArticleLatelyODMan();

            //오늘 날짜를 가져옴
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy / MM / dd");
            String nowdate = df.format(c.getTime());

            while (true) {
                if (cutYear(voidarticle.getOD()) * 10000 + cutMonth(voidarticle.getOD()) * 100 + cutDay(voidarticle.getOD()) > cutYear(nowdate) * 10000 + cutMonth(nowdate) * 100 + cutDay(nowdate)) {
                    break;
                } else {
                    String nameee = voidarticle.getGG();
                    daod = new DaoDead(getApplicationContext());
                    daod.InsertDead(voidarticle.getImg(), voidarticle.getGG(), voidarticle.getJR(), voidarticle.getGJ(), voidarticle.getOD());

                    dao.InsertDataDB("[" + nameee + "](이)가 전역했습니다!");
                    Alramall = Alramall + nameee + "(이)가 전역했습니다. 데이터는 자동으로 전역자 리스트로 이동됩니다.\n\n";
                    alarmed = 1;


                    daom.DeleteData(voidarticle.getGG());
                    refresh();
                    voidarticle = daom.getArticleLatelyODMan();
                }
            }
            int ac = 0;

            ArrayList<MainArticle> checkarticle = daom.getArticleList();
            for (int i = 0; i < checkarticle.size(); i++) {
                String checkname = checkarticle.get(i).getGG();

                try {
                    checkarticleh = daoh.getArticleList(checkname);


                    for (int j = 0; j < checkarticleh.size(); j++) {
                        String minimumdate = checkarticleh.get(j).getARf2();
                        String maximumdate = checkarticleh.get(j).getARs2();

                        if (cutYear(maximumdate) * 10000 + cutMonth(maximumdate) * 100 + cutDay(maximumdate) >= cutYear(nowdate) * 10000 + cutMonth(nowdate) * 100 + cutDay(nowdate) && cutYear(minimumdate) * 10000 + cutMonth(minimumdate) * 100 + cutDay(minimumdate) <= cutYear(nowdate) * 10000 + cutMonth(nowdate) * 100 + cutDay(nowdate)) {

                            ac = 1;
                        }

                        if ((cutYear(maximumdate) * 10000 + cutMonth(maximumdate) * 100 + cutDay(maximumdate)) == (cutYear(nowdate) * 10000 + cutMonth(nowdate) * 100 + cutDay(nowdate))) {
                            if (checkarticleh.get(j).getSnum2().indexOf("0") != -1) {
                                Alramall = Alramall + maximumdate + "의 휴가복귀자 : " + checkarticleh.get(j).getName4() + "\n\n";
                                alarmed = 1;
                                dao.InsertDataDB(checkarticleh.get(j).getName4() + "의 휴가 복귀날입니다! [" + maximumdate + "]");
                                daoh.settingsnumbyListnumber("1", checkarticleh.get(j).getARf2(), checkarticleh.get(j).getARs2());
                            }
                        }
                        if ((cutYear(minimumdate) * 10000 + cutMonth(minimumdate) * 100 + cutDay(minimumdate)) == (cutYear(nowdate) * 10000 + cutMonth(nowdate) * 100 + cutDay(nowdate))) {
                            if (checkarticleh.get(j).getFnum2().indexOf("0") != -1) {
                                Alramall = Alramall + minimumdate + "의 휴가출발자 : " + checkarticleh.get(j).getName4() + "\n\n";
                                alarmed = 1;
                                dao.InsertDataDB(checkarticleh.get(j).getName4() + "의 휴가 복귀날입니다! [" + maximumdate + "]");
                                daoh.settingfnumbyListnumber("1", checkarticleh.get(j).getARf2(), checkarticleh.get(j).getARs2());
                            }
                        }

                    }
                    if (ac == 1) {
                        daom.setINOUTByName(checkarticle.get(i).getGG(), "OUT");
                    } else {
                        daom.setINOUTByName(checkarticle.get(i).getGG(), "IN");
                    }
                    ac = 0;
                } catch (Exception e0) {
                    Log.e("test", "errord" + e0);
                    e0.printStackTrace();
                }

            }


            ArrayList<AlarmArticle> checkarcticleal = daoa.getArticleListpurely();
            for (int k = 0; k < checkarcticleal.size(); k++) {
                String FirstData = checkarcticleal.get(k).getARf();
                String SecondData = checkarcticleal.get(k).getARs();

                int beegyuf = cutYear(FirstData) * 10000 + cutMonth(FirstData) * 100 + cutDay(FirstData);
                int beegyus = cutYear(SecondData) * 10000 + cutMonth(SecondData) * 100 + cutDay(SecondData);
                int beenow = cutYear(nowdate) * 10000 + cutMonth(nowdate) * 100 + cutDay(nowdate);

                if (beegyuf == beenow) {
                    if (checkarcticleal.get(k).getFnum().indexOf("0") != -1) {
                        Alramall = Alramall + checkarcticleal.get(k).getName3() + "의 일정이 오늘입니다 : " + checkarcticleal.get(k).getContent() + "\n [" + FirstData + "]\n\n";
                        alarmed = 1;
                        dao.InsertDataDB(checkarcticleal.get(k).getName3() + "의 일정이 오늘입니다 : " + checkarcticleal.get(k).getContent() + " [" + FirstData + "]");
                        daoa.settingfnumbyListnumber("1", checkarcticleal.get(k).getARf(), checkarcticleal.get(k).getARs(), checkarcticleal.get(k).getContent());
                    }
                }
                if (beegyus == beenow) {
                    if (checkarcticleal.get(k).getSnum().indexOf("0") != -1) {
                        Alramall = Alramall + checkarcticleal.get(k).getName3() + "의 일정에 대한 알람 : " + checkarcticleal.get(k).getContent() + "\n [" + SecondData + "]\n\n";
                        alarmed = 1;
                        dao.InsertDataDB(checkarcticleal.get(k).getName3() + "의 일정에 대한 알람 : " + checkarcticleal.get(k).getContent() + " [" + SecondData + "]");
                        daoa.settingsnumbyListnumber("1", checkarcticleal.get(k).getARf(), checkarcticleal.get(k).getARs(), checkarcticleal.get(k).getContent());
                    }
                }
                if (beegyus == beenow) {

                }
            }


        } catch (Exception e2) {
            Log.e("test", "errord" + e2);
            e2.printStackTrace();
        }

        if(alarmed == 1) {
            showsimpledialog("알 람", Alramall);
        }
        //------------------------------------------------------------------------------------------------------------------------------------정열 스피너에 관한 초기화
        jungryulSp = (Spinner) findViewById(R.id.spinner);
        jungryulSp.setOnItemSelectedListener(this);
        ArrayAdapter jungryulAdapter = ArrayAdapter.createFromResource(this, R.array.jungtyul, android.R.layout.simple_spinner_item);
        jungryulAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jungryulSp.setAdapter(jungryulAdapter);


        //------------------------------------------------------------------------------------------------------------------------------------부가적인 함수들

    }
    //정렬에 따른 데이터 배열 초기화
    public void refresh() {
        articlelist = daom.getArticleList();
        customAdapter3 = new CustomAdapter3(this, R.layout.mainlistlayout, articlelist);
        listview.setAdapter(customAdapter3);
    }
    public void refresh0() {
        daom = new DaoMain(getApplicationContext());

        articlelist = daom.getArticleList0();

        customAdapter3 = new CustomAdapter3(this, R.layout.mainlistlayout, articlelist);
        listview.setAdapter(customAdapter3);
    }
    public void refresh1() {
        daom = new DaoMain(getApplicationContext());

        articlelist = daom.getArticleList1();

        customAdapter3 = new CustomAdapter3(this, R.layout.mainlistlayout, articlelist);
        listview.setAdapter(customAdapter3);
    }
    public void refresh2() {
        daom = new DaoMain(getApplicationContext());

        articlelist = daom.getArticleList2();

        customAdapter3 = new CustomAdapter3(this, R.layout.mainlistlayout, articlelist);
        listview.setAdapter(customAdapter3);
    }
    public void refresh3() {
        daom = new DaoMain(getApplicationContext());

        articlelist = daom.getArticleList3();

        customAdapter3 = new CustomAdapter3(this, R.layout.mainlistlayout, articlelist);
        listview.setAdapter(customAdapter3);
    }
    public void refresh4() {
        daom = new DaoMain(getApplicationContext());

        articlelist = daom.getArticleList4();

        customAdapter3 = new CustomAdapter3(this, R.layout.mainlistlayout, articlelist);
        listview.setAdapter(customAdapter3);
    }

    //날짜값에서 년,월,일을 각각 불러오는 함수들
    public int cutYear(String a) {
        return Integer.parseInt(a.substring(0, 4));
    }
    public int cutMonth(String a) {
        return Integer.parseInt(a.substring(7, 9));
    }
    public int cutDay(String a) {
        return Integer.parseInt(a.substring(12, 14));
    }

    //알림창을 띄우는 간단한 함수
    public void showsimpledialog(String title, String content){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setNeutralButton("확인",dialogClickListener);
        builder.show();
    }

    //------------------------------------------------------------------------------------------------------------------------------------
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked


                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    daom.DeleteData(deletingstr);
                    daoa.DeleteData(deletingstr);
                    daoh.DeleteData(deletingstr);
                    try {
                        dao.InsertDataDB("[" + deletingstr + "] (이)가 리스트에서 제거됨");
                    } catch (Exception e) {
                        Log.e("test", "error" + e);
                        e.printStackTrace();
                    }

                    refresh();
                    break;
                case DialogInterface.BUTTON_NEUTRAL:

                    break;
            }
        }
    };

    //------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (deleteNum == 0) {
            Intent intent = new Intent(this, AddActivity.class);
            intent.putExtra("BD", articlelist.get(position).getBD() + "");
            startActivity(intent);
        } else {
            deletingstr = articlelist.get(position).getGG();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("삭제 명령");
            builder.setMessage("이 병사를 목록에서 제거하겠습니까?");
            builder.setNegativeButton("예", dialogClickListener);
            builder.setPositiveButton("아니오", dialogClickListener);

            builder.show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent4 = new Intent(this, LogActivity.class);
                startActivity(intent4);
                break;
            case R.id.imageButton:
                Intent intent2 = new Intent(this, PListActivity.class);
                intent2.putExtra("Change","0");
                startActivity(intent2);
                break;
            case R.id.button2:
                Intent intent3 = new Intent(this, DListActivity.class);
                startActivity(intent3);
                break;
            case R.id.imageButton2:
                if (deleteNum == 1) {
                    deleteNum = 0;
                    imgbut3.setImageResource(android.R.drawable.ic_menu_delete);
                    textView1.setText("");

                } else if (deleteNum == 0) {
                    deleteNum = 1;
                    imgbut3.setImageResource(android.R.drawable.ic_delete);
                    textView1.setText("지울 병사를 선택");
                }
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                refresh();
                break;
            case 1:
                refresh0();
                break;
            case 2:
                refresh1();
                break;
            case 3:
                refresh2();
                break;
            case 4:
                refresh3();
                break;
            case 5:
                refresh4();
                break;
        }
    }

    private boolean doesUserHavePermission()
    {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

}