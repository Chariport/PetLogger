package com.example.charr.petlogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class weight_log extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_log);
    }

    public void goToPetProfile(View view)
    {
        Intent intent = new Intent(weight_log.this, pet_profile.class);
        startActivity(intent);
    }

    public void addWeightEntry(View view)
    {
        Intent intent = new Intent(weight_log.this, weight_log_entry.class);
        startActivity(intent);
    }
}
