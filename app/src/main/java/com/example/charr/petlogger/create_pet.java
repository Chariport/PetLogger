package com.example.charr.petlogger;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class create_pet extends AppCompatActivity implements View.OnClickListener
{
    final String TAG = this.getClass().getName();

    public Spinner sex, type;
    public Button addPhoto, finish, cancel;
    public TextView name, bday;
    public DatePickerDialog.OnDateSetListener bdayDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pet);

        Log.d(TAG,"in onCreate");

        //to use this add things to the AndroidMaifest.xml...
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setHomeButtonEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setHomeAsUpIndicator();

        //set up buttons
        addPhoto = (Button)findViewById(R.id.addPhotoButton);
        addPhoto.setOnClickListener(this);
        finish = (Button)findViewById(R.id.finishButton);
        finish.setOnClickListener(this);
        cancel = (Button)findViewById(R.id.cancelButton);
        cancel.setOnClickListener(this);

        //set up Spinners
        sex = (Spinner)findViewById(R.id.spinnerSex);
        sex.setPrompt("Sex: ");
        type = (Spinner)findViewById(R.id.spinnerType);
        type.setPrompt("Type: ");
        //set up TextViews
        name = (TextView)findViewById(R.id.petName);
        bday = (TextView) findViewById(R.id.petBday);
        bday.setOnClickListener(this);

        //set up DateSetListener
        bdayDateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                month += 1; // starts at 0 for months...
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + dayOfMonth + "/" + year);
                String date =  month + "/" + dayOfMonth + "/" + year;
                bday.setText(date);
            }
        };
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Log.d(TAG,"in onBackPressed");
        // need to add an alert
        // ex: are you sure you want to exit creating a pet profile...
    }


    @Override
    public void onClick(View v)
    {
        Log.d(TAG,"in onClick");

        switch(v.getId())
        {
            case R.id.petBday:
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        bdayDateSetListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog.show();

                break;
            case R.id.addPhotoButton:
                // logic to add photo...
                break;

            case R.id.cancelButton:
                // need to add are you sure alert...
                Log.d(TAG,"cancel button was clicked");
                Intent cBi = new Intent(create_pet.this, petCardMainPage.class);
                startActivity(cBi);
                break;
            case R.id.finishButton:
                Log.d(TAG,"finish button was clicked");
                // any data validation for name??

                // need to add message on creation:
                //   ex: new pet profile created..edit more pet details on the pets profile...

                String petSex = sex.getSelectedItem().toString();
                String petType = type.getSelectedItem().toString();
                String petName = name.getText().toString();
                String petBday = bday.getText().toString();
                // need something to hold the picture...

                Log.d(TAG,"petSex: " + petSex + " petType: " + petType + " petName: " + petName + " petBday: " + petBday);

                Intent fBi = new Intent(create_pet.this, petCardMainPage.class);
                startActivity(fBi);
                break;
        }
    }
}