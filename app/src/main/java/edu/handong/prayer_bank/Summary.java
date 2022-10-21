package edu.handong.prayer_bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;

public class Summary extends AppCompatActivity {

    ArrayList<Integer> jsonList = new ArrayList<>(); // ArrayList 선언
    ArrayList<String> labelList = new ArrayList<>(); // ArrayList 선언
    //Bar chart
    private BarChart barChart;
    TextView minuteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        //make the main button
        ImageButton praybutton = findViewById(R.id.prayButton);
        ImageButton mypagebutton = findViewById(R.id.mypageButton);
        ImageButton summarybutton = findViewById(R.id.summaryButton);
        ImageButton calenderbutton = findViewById(R.id.calendarButton);
        ImageButton homebutton = findViewById(R.id.homeButton);

        //screen change
        //summary -> pray screen
        praybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pray = new Intent(Summary.this, Prayer.class);
                startActivity(pray);
            }
        });
        //summary -> my page screen
        mypagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mypage = new Intent(Summary.this, MyPage.class);
                startActivity(mypage);
            }
        });
        //summary -> summary screen
        summarybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent summary = new Intent(Summary.this, Summary.class);
                startActivity(summary);
            }
        });
        //summary -> calender
        calenderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calender = new Intent(Summary.this, Calendar.class);
                startActivity(calender);
            }
        });
        //summary -> home
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(Summary.this, MainActivity.class);
            }
        });


        //Bar chart
        barChart = (BarChart) findViewById(R.id.chart_week);
        graphInitSetting();    //그래프 기본 세팅

        BarChartGraph(labelList, jsonList);
        barChart.setTouchEnabled(false); //확대하지 못하게 막아버림
        barChart.getAxisRight().setAxisMaxValue(80);



    }

    public void graphInitSetting(){
        labelList.add("S");
        labelList.add("M");
        labelList.add("T");
        labelList.add("W");
        labelList.add("T");
        labelList.add("F");
        labelList.add("S");

        jsonList.add(10); //element

        BarChartGraph(labelList, jsonList);
    }

    /**
     * 그래프함수
     */
    private void BarChartGraph(ArrayList<String> labelList, ArrayList<Integer> valList) {
        // BarChart 메소드

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < valList.size(); i++) {
            entries.add(new BarEntry((Integer) valList.get(i), i));
        }

        //BarDataSet depenses = new BarDataSet(entries, "")
    }

}