package com.example.app18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Registrations extends AppCompatActivity {
    EditText log, pass;
    Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrations);
        AppDataBase db = AppDataBase.getDbInstance(getApplicationContext());
        log = findViewById(R.id.txtloginReg);
        pass = findViewById(R.id.txtpassReg);
        reg = findViewById(R.id.btnRegister);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String logi = log.getText().toString().replaceAll("\\s+","");
                String passe = pass.getText().toString().replaceAll("\\s+","");
                if(logi !="" && !logi.isEmpty() && passe !="" && !passe.isEmpty()){
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
                        Student student = new Student(logi, passe,0);
                        db.studentDao().InsertStudent(student);
                        Intent ij = new Intent(Registrations.this, Authorizations.class);
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