package com.example.plant_monitoring_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainManu extends AppCompatActivity {

    private Button button_monitor;
    private Button button_pdf_view;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        button_monitor = findViewById(R.id.btn_monitoring);

        
        button_monitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondActivity();
            }
        });



    }

    private void openSecondActivity() {

        Intent i = new Intent(MainManu.this, MonitoringActivity.class);
        startActivity(i);

    }

}