package com.example.charr.petlogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class weight_log_entry extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_log_entry);
    }


    public void backToWeightLog(View view)
    {
        Intent intent = new Intent(weight_log_entry.this, weight_log.class);
        startActivity(intent);
    }

}