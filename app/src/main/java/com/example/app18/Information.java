package com.example.app18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Information extends AppCompatActivity {
    TextView txt;
    Button btn1,btn2;
    SharedPreferences mSettings;
    String APP_PREFERENCES = "setting";
    String APP_PREFERENCES_LOGIN = "LOGIN"; //
    String APP_PREFERENCES_PASSWORD = "PASSWORD"; //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        btn1 = findViewById(R.id.button3);
        btn2 = findViewById(R.id.button4);
        AppDataBase db = AppDataBase.getDbInstance(getApplicationContext());
        txt = findViewById(R.id.INF);
        List<Student> st = db.studentDao().getAllStudent();
        if(st.size()>0){
            for(int i=0;i<st.size();i++){
                txt.append(st.get(i).getId_st()+" / "+st.get(i).getLogin()+" / "+st.get(i).getPassword()+"\n");
            }
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.studentDao().DeleteAllStudent();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSettings.edit().putString(APP_PREFERENCES_LOGIN,"").putString(APP_PREFERENCES_PASSWORD,"").apply();
            }
        });
    }
}