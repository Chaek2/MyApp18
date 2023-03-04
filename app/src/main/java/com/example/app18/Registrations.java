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

public class Registrations extends AppCompatActivity {
    EditText log, pass;
    Button reg;
    TextView txtmain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrations);
        AppDataBase db = AppDataBase.getDbInstance(getApplicationContext());
        log = findViewById(R.id.txtloginReg);
        pass = findViewById(R.id.txtpassReg);
        reg = findViewById(R.id.btnRegister);
        txtmain = findViewById(R.id.maintextReg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String logi = log.getText().toString();
                String passe = pass.getText().toString();
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
                    Student student = new Student(
                            logi,
                            passe
                    );
                    db.studentDao().InsertStudent(student);
                    Intent ij = new Intent(Registrations.this, Authorizations.class);
                    startActivity(ij);
                }
            }
        });
    }
}