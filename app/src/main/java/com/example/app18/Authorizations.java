package com.example.app18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Authorizations extends AppCompatActivity {
    EditText log, pass;
    Button auto,reg;
    TextView txtmain;

    SharedPreferences mSettings;
    String APP_PREFERENCES = "setting";
    String APP_PREFERENCES_LOGIN = "LOGIN"; //
    String APP_PREFERENCES_PASSWORD = "PASSWORD"; //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorizations);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if(!mSettings.getString(APP_PREFERENCES_LOGIN,"").isEmpty()){
            Intent intent = new Intent(Authorizations.this, MainMenu.class);
            startActivity(intent);
        }
        log = findViewById(R.id.txtlogin);
        pass = findViewById(R.id.txtpass);
        auto = findViewById(R.id.btnAuto);
        reg = findViewById(R.id.btnReg);
        txtmain = findViewById(R.id.maintext);

        AppDataBase db = AppDataBase.getDbInstance(getApplicationContext());

        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = db.studentDao().AutoStudent(log.getText().toString(),pass.getText().toString());
                if(student!=null){
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putString(APP_PREFERENCES_LOGIN,student.getLogin());
                    editor.putString(APP_PREFERENCES_PASSWORD,student.getPassword());
                    editor.apply();
                    Intent intent = new Intent(Authorizations.this, MainMenu.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Authorizations.this, Registrations.class);
                startActivity(i);
            }
        });

    }
}