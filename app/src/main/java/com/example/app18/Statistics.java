package com.example.app18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Statistics extends AppCompatActivity {
    EditText log, pass;
    Button reg;
    SharedPreferences mSettings;
    String APP_PREFERENCES = "setting";
    String APP_PREFERENCES_LOGIN = "LOGIN"; //
    String APP_PREFERENCES_PASSWORD = "PASSWORD"; //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        AppDataBase db = AppDataBase.getDbInstance(getApplicationContext());
        log = findViewById(R.id.txtloginReg);
        pass = findViewById(R.id.txtpassReg);
        reg = findViewById(R.id.btnRegister);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        log.setText(db.studentDao().getStudent(mSettings.getString(APP_PREFERENCES_LOGIN,"")).getLogin());
        pass.setText(db.studentDao().getStudent(mSettings.getString(APP_PREFERENCES_LOGIN,"")).getPassword());
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String logi = log.getText().toString().replaceAll("\\s+","");
                String passe = pass.getText().toString().replaceAll("\\s+","");
                if(logi !="" && !logi.isEmpty() && passe !="" && !passe.isEmpty()){
                    int wall = db.studentDao().getStudent(mSettings.getString(APP_PREFERENCES_LOGIN,"")).getWallet();
                    boolean st =false;
                    List<Student> students = db.studentDao().getAllStudent();
                    for (int i = 0;i<students.size();i++){
                        if(students.get(i).getLogin().equals(logi)){
                            Toast.makeText(getApplicationContext(), "Такой логин уже есть",
                                    Toast.LENGTH_LONG).show();
                            st=true;
                        }
                    }
                    if(!st){
                        Student student = new Student(logi, passe,wall);
                        db.studentDao().InsertStudent(student);
                        SharedPreferences.Editor editor = mSettings.edit();
                        editor.putString(APP_PREFERENCES_LOGIN,student.getLogin());
                        editor.putString(APP_PREFERENCES_PASSWORD,student.getPassword());
                        editor.apply();
                        Intent ij = new Intent(Statistics.this, MainMenu.class);
                        startActivity(ij);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Надо заполнить все поля правильно!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}