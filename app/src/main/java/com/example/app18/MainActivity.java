package com.example.app18;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edL, edP;
    Button btnE;
    TextView txtE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edL = findViewById(R.id.edit_login);
        edP = findViewById(R.id.edit_pass);
        btnE = findViewById(R.id.btn_enter);
        txtE = findViewById(R.id.txtEnter);

        fetchStudent();

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtE.setText("");

                AppDataBase db = AppDataBase.getDbInstance(getApplicationContext());
                //db.studentDao().InsertStudent(student);
                fetchStudent();
            }
        });
    }

    public  void fetchStudent(){
        AppDataBase db = AppDataBase.getDbInstance(getApplicationContext());
        List<Student> students = db.studentDao().getAllStudent();
        for (int i = 0;i<students.size();i++){

        }
    }
}