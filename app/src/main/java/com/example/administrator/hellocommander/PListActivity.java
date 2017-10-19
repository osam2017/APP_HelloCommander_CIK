package com.example.administrator.hellocommander;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.widget.DatePicker;
import android.icu.util.Calendar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.provider.MediaStore;
import android.net.Uri;
import android.database.Cursor;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.pm.PackageManager;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.os.Build;
import android.Manifest;
import android.support.v4.content.ContextCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class PListActivity extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener {

    private Button but1;
    private Button but2;
    private Button but3;
    private Button but4;

    private Spinner sp2;
    private Spinner sp3;
    private Spinner sp4;

    private TextView txtv24;
    private EditText ed0;
    private EditText ed3;

    private ImageButton imgbut1;

    private static final int RESULT_LOAD_IMG = 1;
    private String filePath;
    private String fileName;

    private int a = 1;
    private int imgpick = 0;
    private String bitmappath = "";

    private DaoLog daol;
    private DaoMain daom;

    private MainArticle articlem;

    private String CHG;
    private String BDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plist);
        but1 = (Button) findViewById(R.id.button8);
        but1.setOnClickListener(this);

        but2 = (Button) findViewById(R.id.button9);
        but2.setOnClickListener(this);

        but3 = (Button) findViewById(R.id.button10);
        but3.setOnClickListener(this);

        but4 = (Button) findViewById(R.id.button4) ;
        but4.setOnClickListener(this);

        sp2 = (Spinner) findViewById(R.id.spinner2);
        sp3 = (Spinner) findViewById(R.id.spinner3);

        ed0 = (EditText) findViewById(R.id.editText);
        ed3 = (EditText) findViewById(R.id.editText3);

        txtv24 = (TextView) findViewById(R.id.textView24);

        imgbut1 = (ImageButton) findViewById(R.id.imageButton6);
        imgbut1.setOnClickListener(this);

        CHG = getIntent().getExtras().getString("Change");

        daol = new DaoLog(getApplicationContext());
        daom = new DaoMain(getApplicationContext());

        //스피너
        Spinner gunjongSp = (Spinner) findViewById(R.id.spinner3);

        ArrayAdapter gunjongAdapter = ArrayAdapter.createFromResource(this, R.array.gunjong, android.R.layout.simple_spinner_item);
        gunjongAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gunjongSp.setAdapter(gunjongAdapter);

        Spinner GGSp = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter GGAdapter = ArrayAdapter.createFromResource(this, R.array.gyugyup, android.R.layout.simple_spinner_item);
        GGAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        GGSp.setAdapter(GGAdapter);

        if(CHG.indexOf("1") != -1) {
            txtv24.setText(":: CHANGE DATA ::");
            but4.setText("수정");
            imgpick = 1;
            BDD = getIntent().getExtras().getString("BD");
            articlem = daom.getArticleByBirthDay(BDD);

            but1.setText(articlem.getBD());
            but2.setText(articlem.getID());
            but3.setText(articlem.getOD());

            int spn1 = 0;
            switch (articlem.getName())
            {
                case "이병":
                    spn1 = 0;
                    break;
                case "일병":
                    spn1 = 1;
                    break;
                case "상병":
                    spn1 = 2;
                    break;
                case "병장":
                    spn1 = 3;
                    break;
            }

            sp2.setSelection(spn1);

            int spn2 = 0;
            switch (articlem.getJR())
            {
                case "공군":
                    spn2 = 0;
                    break;
                case "해군":
                    spn2 = 1;
                    break;
                case "육군":
                    spn2 = 2;
                    break;
            }
            sp3.setSelection(spn2);
            ed0.setText(articlem.getGG());
            ed3.setText(articlem.getGJ());
        }





    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button8:
                a = 1;
                Calendar tmpCal = Calendar.getInstance();
                DatePickerDialog tmpOPD = new DatePickerDialog(this, this, tmpCal.get(Calendar.YEAR), tmpCal.get(Calendar.MONTH), tmpCal.get(Calendar.DAY_OF_MONTH));
                tmpOPD.show();
                break;
            case R.id.button9:
                a = 2;
                Calendar tmpCal1 = Calendar.getInstance();
                DatePickerDialog tmpOPD2 = new DatePickerDialog(this, this, tmpCal1.get(Calendar.YEAR), tmpCal1.get(Calendar.MONTH), tmpCal1.get(Calendar.DAY_OF_MONTH));
                tmpOPD2.show();
                break;
            case R.id.button10:
                a = 3;
                Calendar tmpCal2 = Calendar.getInstance();
                DatePickerDialog tmpOPD3 = new DatePickerDialog(this, this, tmpCal2.get(Calendar.YEAR), tmpCal2.get(Calendar.MONTH), tmpCal2.get(Calendar.DAY_OF_MONTH));
                tmpOPD3.show();
                break;
            case R.id.imageButton6:
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
                builder.setTitle("이미지 선택");
                builder.setNegativeButton("파일 선택", dialogClickListener);
                builder.setPositiveButton("기본 이미지 선택", dialogClickListener);

                builder.show();

                break;
            case R.id.button4:
                    if(imgpick ==1){
                        try{
                            if(CHG.indexOf("1") != -1) {
                                daom.DeleteData(articlem.getGG());
                                daom.InsertMain(bitmappath, sp2.getSelectedItem().toString(), ed0.getText().toString(), sp3.getSelectedItem().toString(), ed3.getText().toString(), but1.getText().toString(), but2.getText().toString(), but3.getText().toString(), "IN");
                                daol.InsertDataDB("[" + ed0.getText().toString() + "](이)가 수정되었습니다");
                            }else {
                                daom.InsertMain(bitmappath, sp2.getSelectedItem().toString(), ed0.getText().toString(), sp3.getSelectedItem().toString(), ed3.getText().toString(), but1.getText().toString(), but2.getText().toString(), but3.getText().toString(), "IN");
                                daol.InsertDataDB("[" + ed0.getText().toString() + "](이)가 목록에 추가되었습니다");
                            }
        } catch (Exception e){
            Log.e("test","error" + e);
            e.printStackTrace();
        }
                finish();
                }
                else
                    {
                        Toast.makeText(getApplicationContext(),"이미지를 선택해주세요.", Toast.LENGTH_LONG).show();
                    }
                break;
        }
    }

@Override
protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
        try {
            final Uri imageUri = data.getData();
            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            bitmappath = getRealPathFromURI(imageUri);
            imgbut1.setImageBitmap(selectedImage);
            imgpick = 1;

        } catch (FileNotFoundException e) {
        e.printStackTrace();
        Toast.makeText(PListActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

        }else {
        Toast.makeText(PListActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
        }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        switch (a)
        {
            case 1:
                but1.setText(String.format("%04d / %02d / %02d", year, month +1, dayOfMonth ));
                break;
            case 2:
                but2.setText(String.format("%04d / %02d / %02d", year, month +1, dayOfMonth ));
                break;
            case 3:
                but3.setText(String.format("%04d / %02d / %02d", year, month +1, dayOfMonth ));
                break;

        }

    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };

        CursorLoader cursorLoader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    bitmappath = "def.png";
                    imgpick = 1;

                    imgbut1.setImageResource(R.drawable.def);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:

                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

                    break;
            }
        }
    };
}