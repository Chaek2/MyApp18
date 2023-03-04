package com.example.app18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainMenu extends AppCompatActivity {
    Button pref, stud,all;
    TextView txt;
    SharedPreferences mSettings;
    String APP_PREFERENCES = "setting";
    String APP_PREFERENCES_LOGIN = "LOGIN"; //
    String APP_PREFERENCES_PASSWORD = "PASSWORD"; //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        AppDataBase db = AppDataBase.getDbInstance(getApplicationContext());
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        txt = findViewById(R.id.txtall);
        pref = findViewById(R.id.delpref);
        stud = findViewById(R.id.delstud);
        all = findViewById(R.id.selectall);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ars = mSettings.getString(APP_PREFERENCES_LOGIN,"")+" : "+mSettings.getString(APP_PREFERENCES_PASSWORD,"");
                boolean ar = mSettings.getString(APP_PREFERENCES_LOGIN,"").isEmpty();
                Toast.makeText(getApplicationContext(), ar + ":"+ ars,
                        Toast.LENGTH_LONG).show();
                List<Student> st = db.studentDao().getAllStudent();
                for(int i = 0;i<db.studentDao().getAllStudent().size();i++){
                    txt.append(st.get(i).getId_st()+" : "+st.get(i).getLogin()+" : "+st.get(i).getPassword()+"\n");
                }

            }
        });
        pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSettings.edit().putString(APP_PREFERENCES_LOGIN,null).putString(APP_PREFERENCES_PASSWORD,null).apply();
            }
        });
        stud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.studentDao().DeleteAllStudent();
            }
        });
    }
}