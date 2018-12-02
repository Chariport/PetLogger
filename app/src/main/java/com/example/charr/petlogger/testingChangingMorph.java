package com.example.charr.petlogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class testingChangingMorph extends AppCompatActivity
{
    final String TAG = this.getClass().getName();

    private card_list cardList = card_list.getmInstance();

    public Intent intent;
    public Button backtoHome;
    public TextView morph;

    public card_item currentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_changing_morph);


        // get intent - and its info
        intent = getIntent();

        // get current card object
        currentCard = cardList.getObjectAtIndex(intent.getExtras().getInt("indexInArrayList"));

        //set up buttons
        backtoHome = findViewById(R.id.backToHome);

        //set up textviews
        morph = findViewById(R.id.testingMorphValue);
        String currentMorphValue = currentCard.getMorph();
        morph.setText(currentMorphValue);
    }


    public void backToHome(View view)
    {
        // means finish button was clicked, so save values


        // prob need data validation
        currentCard.setMorph(morph.getText().toString().trim());

        Intent intent = new Intent(testingChangingMorph.this, home_page.class);
        startActivity(intent);
    }
}
