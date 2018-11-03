package com.example.charr.petlogger;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.Base64;
import java.util.Calendar;

public class create_pet extends AppCompatActivity implements View.OnClickListener
{
    final String TAG = this.getClass().getName();

    public Spinner sex;
    public Button finish, cancel;
    public TextView name, bday;
    public DatePickerDialog.OnDateSetListener bdayDateSetListener;
    public ImageView petImage;
    private static final int RESULT_LOAD_IMAGE = 1;

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
                String date =  month + "/" + dayOfMonth + "/" + year;
                bday.setText(date);
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
        // need to add an alert
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
                String petName = name.getText().toString();
                String petBday = bday.getText().toString();

                // Get Bitmap of image
                Bitmap petProfileImage = ((BitmapDrawable)petImage.getDrawable()).getBitmap();

                // compress image..not sure if this is necessary
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                petProfileImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                String encodedImgae = android.util.Base64.encodeToString(byteArrayOutputStream.toByteArray(),android.util.Base64.DEFAULT);


                Log.d(TAG,"petSex: " + petSex + " petName: " + petName + " petBday: " + petBday);

                Intent fBi = new Intent(create_pet.this, petCardMainPage.class);
                startActivity(fBi);
                break;

             case R.id.pet_imageView:
                 Log.d(TAG,"clicked on image view!! son!@");
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
            Uri selectedImage = data.getData(); //address of image
            petImage.setImageURI(selectedImage);
        }
    }
}