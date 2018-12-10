package com.example.charr.petlogger;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class create_pet extends AppCompatActivity implements View.OnClickListener
{
    final String TAG = this.getClass().getName();

    public Spinner sex;
    public Button finish, cancel;
    public TextView name, bday, weight;
    public NumberPicker leftOfDecimal, rightOfDecimal;
    public DatePickerDialog.OnDateSetListener bdayDateSetListener;
    public ImageView petImage;
    private static final int RESULT_LOAD_IMAGE = 1;
    public boolean gotImage = false; //used to see if user selected an image;
    public String tempDate = "";

    public String petSex, petName;
    public java.util.Date petBdate;
    public double petWeight;

    private card_list cardList = card_list.getmInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG,"in onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pet);


        //set up buttons
        finish = (Button)findViewById(R.id.finishButton);
        finish.setOnClickListener(this);
        cancel = (Button)findViewById(R.id.cancelButton);
        cancel.setOnClickListener(this);


        //set up Spinners
        sex = (Spinner)findViewById(R.id.spinnerSex);
        ArrayAdapter<CharSequence> sexadapter = ArrayAdapter.createFromResource(
                this, R.array.create_pet_sex, R.layout.spinner_layout);
        sexadapter.setDropDownViewResource(R.layout.spinner_layout);
        sex.setAdapter(sexadapter);


        //set up TextViews
        weight = (TextView) findViewById(R.id.edittextWeight);
        weight.setText("0.0");
        name = (TextView)findViewById(R.id.edittext_name);
        bday = (TextView) findViewById(R.id.edittext_birthdate);
        bday.setOnClickListener(this);


        //set up DateSetListener
        bdayDateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                month += 1; // starts at 0 for months...
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + dayOfMonth + "/" + year);

                tempDate =  month + "/" + dayOfMonth + "/" + year;
                Calendar tempC = Calendar.getInstance();
                tempC.set(year, month - 1, dayOfMonth);
                petBdate = tempC.getTime();

                if(tempDate.length() > 0) // Error set needs to be in here
                    bday.setError(null);
                bday.setText(tempDate);
            }
        };

        //set up imageView
        petImage = (ImageView)findViewById(R.id.pet_imageView);
        petImage.setOnClickListener(this);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Log.d(TAG,"in onBackPressed");
        // maybe add alert on this
        // ex: are you sure you want to exit creating a pet profile...
    }

    public void addPetImage(View v)
    {
        Log.d(TAG,"in addpetimage....");
    }

    @Override
    public void onClick(View v)
    {
        Log.d(TAG,"in onClick");

        switch(v.getId())
        {
            case R.id.edittext_birthdate:
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
            case R.id.cancelButton:
                // need to add are you sure alert...maybe
                Log.d(TAG,"cancel button was clicked");
                Intent cBi = new Intent(create_pet.this, home_page.class);
                startActivity(cBi);
                break;
            case R.id.finishButton:
                Log.d(TAG,"finish button was clicked");

                // get values from textViews
                initialize();

                // get image
                Bitmap petProfileImage;
                String encodedImage;
                if(gotImage)
                {
                    // Get Bitmap of image
                    petProfileImage = ((BitmapDrawable)petImage.getDrawable()).getBitmap();
                    // compress image..not sure if this is necessary
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    petProfileImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    encodedImage = android.util.Base64.encodeToString(byteArrayOutputStream.toByteArray(),android.util.Base64.DEFAULT);
                }
                else
                {
                    // set petProfileImage to a default image
                    // set petProfileImage to a default image....
                    petProfileImage = BitmapFactory.decodeResource(getResources(),R.drawable.defaultpetimage);
                }

                //Log.d(TAG,"petSex: " + petSex + " petName: " + petName + " petBday: " + petBdate.toString() + "petWeight: " + petWeight);
                //Log.d(TAG,"gotImage: " + gotImage);


                if(petSex.equals("Male ♂")) {petSex = "Male ♂";}
                else if (petSex.equals("Female ♀")) {petSex = "Female ♀";}

                // data validation
                if(!validateData())
                {
                    cardList.addToArray(new card_item(petProfileImage, petName, petBdate, petSex, petWeight));

                    // maybe add a message saying pet profile created
                    Intent fBi = new Intent(create_pet.this, home_page.class);
                    startActivity(fBi);

                }
                else
                {
                    //toast up saying there are entry errors
                    Toast.makeText(this, "Profile NOT created. See errors above.", Toast.LENGTH_LONG).show();
                }

                break;

             case R.id.pet_imageView:
                 Log.d(TAG,"clicked on image view");
                 Intent galleryIntent = new Intent(
                         Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                 break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)
        {
            gotImage = true;
            Uri selectedImage = data.getData(); //address of image
            petImage.setImageURI(selectedImage);
        }
    }


    public void initialize()
    {
        petSex = sex.getSelectedItem().toString().trim();
        petName = name.getText().toString().trim();
        // petBdate set in the calendar thing
        petWeight = Double.parseDouble(weight.getText().toString().trim());
    }


    public boolean validateData()
    {
        boolean foundError = false;
        if(petName.isEmpty() || petName.length() > 16)
        {
            name.setError("Name cannot be empty or exceed 16 characters.");
            foundError = true;
        }

        if(tempDate.equals(""))
        {
            bday.setError("Must enter a birth date.");
            foundError = true;
        }
        else
            bday.setError(null);

        int integerPlaces = Double.toString(petWeight).indexOf(".");
        int decimalPlaces = Double.toString(petWeight).length() - integerPlaces - 1;
        // if integerPlaces == -1 then there is no decimal in the number...
        Log.d(TAG,"NUMBER OF INTEGER PLACES:: " + integerPlaces + "  number of decimal places:; " + decimalPlaces );

        if(petWeight == 0.0 || (decimalPlaces > 3  && integerPlaces != -1))
        {
            weight.setError("Weight cannot be empty and no more than 3 decimal places (ex: 00.123)");
            foundError = true;
        }
        else
            weight.setError(null);

        return foundError;
    }
}