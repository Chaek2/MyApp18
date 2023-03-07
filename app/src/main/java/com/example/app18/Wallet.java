package com.example.app18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Wallet extends AppCompatActivity {
    PieChart pieChart;
    Switch sw;
    AppDataBase db;
    SharedPreferences mSettings;
    String APP_PREFERENCES = "setting";
    String APP_PREFERENCES_LOGIN = "LOGIN"; //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        db = AppDataBase.getDbInstance(getApplicationContext());
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        sw = findViewById(R.id.SWdiagram);
        pieChart = findViewById(R.id.diagram);
        setupchar();
        loadPie();
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupchar();
                loadPie();
            }
        });
    }

    ArrayList<PieEntry> loadProc(){
        int cash=0,trans=0,org=0,inv=0,fu;
        List<Account> accounts = db.accountDao().getAccountReplenishment(sw.isChecked(),db.studentDao().getStudent(mSettings.getString(APP_PREFERENCES_LOGIN,"")).getId_st());
        for(int i=0;i<accounts.size();i++){
            int cat = accounts.get(i).getCategory();
            switch (cat){
                case 0:
                    cash+=accounts.get(i).getMoney();
                    break;
                case 1:
                    trans+=accounts.get(i).getMoney();
                    break;
                case 2:
                    org+=accounts.get(i).getMoney();
                    break;
                case 3:
                    inv+=accounts.get(i).getMoney();
                    break;
            }
        }
        fu=cash+trans+org+inv;
        float ca = (float) cash*100/fu;
        float tr = (float) trans*100/fu;
        float or = (float) org*100/fu;
        float in = (float) inv*100/fu;
        ArrayList<PieEntry> entries = new ArrayList<>();
        if(ca!=0) entries.add(new PieEntry(ca,"Cash"));
        if(tr!=0)entries.add(new PieEntry(tr,"Transfers"));
        if(or!=0)entries.add(new PieEntry(or,"Organizations"));
        if(in!=0)entries.add(new PieEntry(in,"Investments"));
        return entries;
    }

    void loadPie(){
        ArrayList<PieEntry> entries = loadProc();

        ArrayList<Integer> colors = new ArrayList<>();
        for(int color: ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }
        for(int color: ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries,"Replenishment");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(R.color.white);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateXY(3000,3000, Easing.EaseInCubic);
    }

    void  setupchar(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelColor(R.color.white);
        pieChart.setEntryLabelTextSize(12f);
        if(sw.isChecked()) pieChart.setCenterText("Доход");
        else pieChart.setCenterText("Расходы");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);


        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false); //вкл-выкл

    }
}