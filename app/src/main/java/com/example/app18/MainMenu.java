package com.example.app18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainMenu extends AppCompatActivity {
    Button st,up,wa,ex;
    TextView wall, log;
    SharedPreferences mSettings;
    String APP_PREFERENCES = "setting";
    String APP_PREFERENCES_LOGIN = "LOGIN"; //
    String APP_PREFERENCES_PASSWORD = "PASSWORD"; //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        AppDataBase db = AppDataBase.getDbInstance(getApplicationContext());
        wall = findViewById(R.id.WalletINF);
        log = findViewById(R.id.StudentINF);
        st = findViewById(R.id.UPWallet);
        up = findViewById(R.id.UpdateStudent);
        wa = findViewById(R.id.addWallet);
        ex = findViewById(R.id.Exit);
        int as = db.studentDao().getStudent(mSettings.getString(APP_PREFERENCES_LOGIN,"")).getWallet();
        wall.append(as+"");
        log.append(mSettings.getString(APP_PREFERENCES_LOGIN,""));
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenu.this, Information.class);
                startActivity(i);
                finish();
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenu.this, Statistics.class);
                startActivity(i);
            }
        });
        wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenu.this, Wallet.class);
                startActivity(i);
            }
        });
        ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSettings.edit().putString(APP_PREFERENCES_LOGIN,"").putString(APP_PREFERENCES_PASSWORD,"").apply();
                Intent i = new Intent(MainMenu.this, Authorizations.class);
                startActivity(i);
            }
        });
    }
}