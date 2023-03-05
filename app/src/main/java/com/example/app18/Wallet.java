package com.example.app18;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Switch;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Wallet extends AppCompatActivity {
    PieChart pieChart;
    Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        sw = findViewById(R.id.SWdiagram);
        pieChart = findViewById(R.id.diagram);
        setupchar();
        loadPie();
    }

    void loadPie(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.5f,"Cash"));
        entries.add(new PieEntry(0.06f,"Transfers"));
        entries.add(new PieEntry(0.3f,"Organizations"));
        entries.add(new PieEntry(0.14f,"Investments"));

        ArrayList<Integer> colors = new ArrayList<>();
        //colors.add(R.color.Cash);
        //colors.add(R.color.Transfers);
        //colors.add(R.color.Organizations);
        //colors.add(R.color.Investments);
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
    }

    void  setupchar(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelColor(R.color.white);
        pieChart.setEntryLabelTextSize(12f);
        pieChart.setCenterText("Расходы");
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