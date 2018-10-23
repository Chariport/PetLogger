package com.example.charr.petlogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class petCardMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_card_main_page);
    }

    public void createPet(View v)
    {
        startActivity(new Intent(petCardMainPage.this, create_pet.class));
    }
}
