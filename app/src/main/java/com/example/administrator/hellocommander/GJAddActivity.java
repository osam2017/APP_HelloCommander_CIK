package com.example.administrator.hellocommander;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

public class GJAddActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gjadd);

        Button but1 = (Button) findViewById(R.id.button6);
        but1.setOnClickListener(this);

        //TextView txt1 = (TextView) findViewById(R.id.textView19);

        DaoBJR dao = new DaoBJR(getApplicationContext());

        editText2 = (EditText) findViewById(R.id.editText2);

        try {
            BJRArticle article11 = dao.getBJRArticleListbynum("2ban");
            //txt1.setText(article11.getJR());
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"error" + e,Toast.LENGTH_LONG).show();
            Log.e("test","error" + e);
            e.printStackTrace();
        }



        //txt1.setText(daob.getBJRArticleListbynum(0));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button6:
                DaoBJR dao = new DaoBJR(getApplicationContext());
                dao.InsertDataDB(editText2.getText().toString());
                finish();
                break;
        }
    }
}
