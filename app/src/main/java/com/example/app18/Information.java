package com.example.app18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

import java.util.List;

public class Information extends AppCompatActivity {
    TextView _wall;
    EditText edWallet;
    Button up;
    Switch sw;
    AppDataBase db;
    RadioGroup rg;
    RadioButton rb0,rb1,rb2,rb3;
    SharedPreferences mSettings;
    String APP_PREFERENCES = "setting";
    String APP_PREFERENCES_LOGIN = "LOGIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        db = AppDataBase.getDbInstance(getApplicationContext());
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        _wall = findViewById(R.id.CountWallet);
        _wall.append(db.studentDao().getStudent(mSettings.getString(APP_PREFERENCES_LOGIN,"")).getWallet()+".");
        sw = findViewById(R.id.swBalance);
        edWallet = findViewById(R.id.EDMoney);
        rg = findViewById(R.id.RG);
        rb0 = findViewById(R.id.rb0);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        up = findViewById(R.id.btnWallet);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb0.isChecked() ||rb1.isChecked() ||rb2.isChecked() ||rb3.isChecked()){
                    if(!edWallet.getText().toString().isEmpty()){
                        if(isNumeric(edWallet.getText().toString())) {
                            int money = Integer.parseInt(edWallet.getText().toString());
                            int moneyD = db.studentDao().getStudent(mSettings.getString(APP_PREFERENCES_LOGIN,"")).getWallet();
                            if (money > 0) {
                                if(sw.isChecked()) AddAccount(money);
                                if(!sw.isChecked()&&moneyD>=money) AddAccount(money);
                            }
                        }
                    }
                }
            }
        });
    }
    public static boolean isNumeric(String str) {
        int mo=0;
        try {
            mo = Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public  void AddAccount(int money){
        int categ = 0;
        if (rb0.isChecked()) {
            categ = 0;
        }
        if (rb1.isChecked()) {
            categ = 1;
        }
        if (rb2.isChecked()) {
            categ = 2;
        }
        if (rb3.isChecked()) {
            categ = 3;
        }
        Account account = new Account(db.studentDao().getStudent(mSettings.getString(APP_PREFERENCES_LOGIN, "")).getId_st(),
                sw.isChecked(), categ, money);
        db.accountDao().InsertAccount(account);
        Student student = db.studentDao().getStudent(mSettings.getString(APP_PREFERENCES_LOGIN, ""));
        if(sw.isChecked()) student.setWallet(student.getWallet()+money);
        else student.setWallet(student.getWallet()-money);
        db.studentDao().UpdateStudent(student);
        Intent i = new Intent(Information.this, MainMenu.class);
        startActivity(i);
    }
}
