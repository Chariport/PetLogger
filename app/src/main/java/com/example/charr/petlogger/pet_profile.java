package com.example.charr.petlogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class pet_profile extends AppCompatActivity {
    private Button backButton;
    private Button weightLogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile);

        backButton = (Button) findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainPage();
            }
        });


    }

    public void openMainPage(){
        Intent intent = new Intent(this, petCardMainPage.class);
        startActivity(intent);
    }

    public void openWeightLog(){
        Intent intent = new Intent(this, weight_log.class);
        startActivity(intent);
    }

    public void viewWeightLog(View view)
    {
        Intent intent = new Intent(pet_profile.this, weight_log.class);
        startActivity(intent);
    }
}
