package com.example.charr.petlogger;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;

//import de.hdodenhof.circleimageview.CircleImageView;

public class pet_profile extends AppCompatActivity implements View.OnClickListener {

    final String TAG = this.getClass().getName();

    private Button backButton;
    private Button weightLogButton;
    private Button editButton;
    private EditText nameEditTextView;
    private EditText ageEditTextView;
    private DatePickerDialog.OnDateSetListener bdayDateSetListener;
    private EditText lastFedEditTextView;
    private EditText weightEditTextView;
    private NumberPicker leftOfDecimal, rightOfDecimal;
    private EditText lastShedEditTextView;
    private Spinner sexSpinner;
    private EditText morphEditTextView;
    private de.hdodenhof.circleimageview.CircleImageView profilePicture;
    private static final int RESULT_LOAD_IMAGE = 1;
    private boolean gotImage = false; //used to see if user selected an image;
    private String tempDate = "";

    public String petSex, petName;
    public java.util.Date petBdate;
    public double petWeight;

    ArrayAdapter<CharSequence> sexadapter;
    int buttonClicked = 1; // If the edit button has been clicked once (begin editing) or twice (finished editing)
    private card_list cardList = card_list.getmInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile);

        // Initializes text boxes and sets disables them
        initializeEditTextViews();

        // Get specific card index
        card_item currentCard = getCurrentCard();

        // Display pet information in EditText fields
        displayPetInfo(currentCard);
    }

    public void openMainPage() {
        Intent intent = new Intent(this, home_page.class);
        startActivity(intent);
    }

    public void openWeightLog() {
        Intent intent = new Intent(this, weight_log.class);
        startActivity(intent);
    }

    public void viewWeightLog(View view) {
        Intent intent = new Intent(pet_profile.this, weight_log.class);
        startActivity(intent);
    }

    public card_item getCurrentCard() {
        Intent cardIntent = getIntent();
        int index = cardIntent.getExtras().getInt("indexInArrayList");
        return cardList.getArray().get(index);
    }

    public void initializeEditTextViews() {

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        editButton = (Button) findViewById(R.id.editButton);
        editButton.setOnClickListener(this);

        nameEditTextView = (EditText) findViewById(R.id.nameEditTextView);
        nameEditTextView.setEnabled(false);

        ageEditTextView = (EditText) findViewById(R.id.ageEditTextView);
        ageEditTextView.setOnClickListener(this);
        ageEditTextView.setEnabled(false);

        //set up DateSetListener
        bdayDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1; // starts at 0 for months...
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + dayOfMonth + "/" + year);

                tempDate = month + "/" + dayOfMonth + "/" + year;
                Calendar tempC = Calendar.getInstance();
                tempC.set(year, month - 1, dayOfMonth);
                petBdate = tempC.getTime();

                if (tempDate.length() > 0) // Error set needs to be in here
                    ageEditTextView.setError(null);
                ageEditTextView.setText(tempDate);
            }
        };

        lastFedEditTextView = (EditText) findViewById(R.id.lastFedEditTextView);
        lastFedEditTextView.setEnabled(false);

        weightEditTextView = (EditText) findViewById(R.id.weightEditTextView);
        weightEditTextView.setEnabled(false);

        lastShedEditTextView = (EditText) findViewById(R.id.lastShedEditTextView);
        lastShedEditTextView.setEnabled(false);

        sexSpinner = (Spinner) findViewById(R.id.sexSpinner);
        sexadapter = ArrayAdapter.createFromResource(
                this, R.array.create_pet_sex, R.layout.spinner_layout);
        sexadapter.setDropDownViewResource(R.layout.spinner_layout);
        sexSpinner.setAdapter(sexadapter);
        sexSpinner.setEnabled(false);

        morphEditTextView = (EditText) findViewById(R.id.morphEditTextView);
        morphEditTextView.setEnabled(false);

        profilePicture = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.profilePicture);
        profilePicture.setOnClickListener(this);
    }

    public void displayPetInfo(card_item currentCard) {
        nameEditTextView.setText(currentCard.getName());
        weightEditTextView.setText(Double.toString(currentCard.getCurrentWeight()) + " g");
        profilePicture.setImageBitmap(currentCard.getImage());
        ageEditTextView.setText(currentCard.dateObjectToMonthDayYearString(currentCard.getBirthDate()));
        lastShedEditTextView.setText(currentCard.dateObjectToMonthDayYearString(currentCard.getLastFed()));
        lastFedEditTextView.setText(currentCard.dateObjectToMonthDayYearString(currentCard.getLastFed()));
        morphEditTextView.setText(currentCard.getMorph());


        int spinnerPosition = sexadapter.getPosition(currentCard.getSex());
        sexSpinner.setSelection(spinnerPosition);

    }


    // Insert switch statement for each text field
    @Override
    public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ageEditTextView:
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
                case R.id.pet_imageView:
                    Log.d(TAG, "clicked on image view");
                    Intent galleryIntent = new Intent(
                            Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                    break;
                case R.id.editButton:
                    // edit button clicked once (begin editing)
                    if (buttonClicked == 1) {
                        nameEditTextView.setBackgroundColor(Color.parseColor("#f7f6f2"));
                        nameEditTextView.setCursorVisible(true);
                        nameEditTextView.setFocusableInTouchMode(true);
                        nameEditTextView.setInputType(InputType.TYPE_CLASS_TEXT);
                        nameEditTextView.requestFocus(); //to trigger the soft input
                        nameEditTextView.setEnabled(true);

                        ageEditTextView.setBackgroundColor(Color.parseColor("#f7f6f2"));
                        ageEditTextView.setEnabled(true);
                        ageEditTextView.setFocusable(false);

                        lastFedEditTextView.setBackgroundColor(Color.parseColor("#f7f6f2"));
                        lastFedEditTextView.setCursorVisible(true);
                        lastFedEditTextView.setFocusableInTouchMode(true);
                        lastFedEditTextView.setInputType(InputType.TYPE_CLASS_TEXT);
                        lastFedEditTextView.requestFocus(); //to trigger the soft input
                        lastFedEditTextView.setEnabled(true);

                        weightEditTextView.setBackgroundColor(Color.parseColor("#f7f6f2"));
                        weightEditTextView.setCursorVisible(true);
                        weightEditTextView.setFocusableInTouchMode(true);
                        weightEditTextView.setInputType(InputType.TYPE_CLASS_TEXT);
                        weightEditTextView.requestFocus(); //to trigger the soft input
                        weightEditTextView.setEnabled(true);

                        lastShedEditTextView.setBackgroundColor(Color.parseColor("#f7f6f2"));
                        lastShedEditTextView.setCursorVisible(true);
                        lastShedEditTextView.setFocusableInTouchMode(true);
                        lastShedEditTextView.setInputType(InputType.TYPE_CLASS_TEXT);
                        lastShedEditTextView.requestFocus(); //to trigger the soft input
                        lastShedEditTextView.setEnabled(true);

                        sexSpinner.setBackgroundColor(Color.parseColor("#f7f6f2"));
                        sexSpinner.setFocusableInTouchMode(true);
                        sexSpinner.requestFocus(); //to trigger the soft input
                        sexSpinner.setEnabled(true);

                        morphEditTextView.setBackgroundColor(Color.parseColor("#f7f6f2"));
                        morphEditTextView.setCursorVisible(true);
                        morphEditTextView.setFocusableInTouchMode(true);
                        morphEditTextView.setInputType(InputType.TYPE_CLASS_TEXT);
                        morphEditTextView.requestFocus(); //to trigger the soft input
                        morphEditTextView.setEnabled(true);

                        //backButton.setEnabled(false);

                        buttonClicked = 0;
                    }

                    // edit button clicked twice (finished editing)
                    else {
                        card_item currentCard = getCurrentCard();
                        String stringInput;
                        double doubleInput = 0;
                        Date dateInput;


                        stringInput = nameEditTextView.getText().toString();
                        currentCard.setmName(stringInput);
                        nameEditTextView.setText(stringInput);
                        nameEditTextView.setBackgroundColor(Color.TRANSPARENT);
                        nameEditTextView.setEnabled(false);

                        currentCard.setmBdate(petBdate);
                        ageEditTextView.setText(ageEditTextView.getText());
                        //ageEditTextView.setText(Integer.toString(currentCard.getAge(currentCard.getBirthDate())));
                        ageEditTextView.setBackgroundColor(Color.TRANSPARENT);
                        ageEditTextView.setEnabled(false);

                        stringInput = lastFedEditTextView.getText().toString();
                        //currentCard.setmName(stringInput);
                        lastFedEditTextView.setText(stringInput);
                        lastFedEditTextView.setBackgroundColor(Color.TRANSPARENT);
                        lastFedEditTextView.setEnabled(false);

//                        stringInput = weightEditTextView.getText().toString();
//                        doubleInput = Double.parseDouble(stringInput);
//                        currentCard.setmWeight(doubleInput);
//                        weightEditTextView.setText(Double.toString(doubleInput) + " g");
//                        weightEditTextView.setBackgroundColor(Color.TRANSPARENT);
//                        weightEditTextView.setEnabled(false);

                        stringInput = lastShedEditTextView.getText().toString();
                        //currentCard.setmName(stringInput);
                        lastShedEditTextView.setText(stringInput);
                        lastShedEditTextView.setBackgroundColor(Color.TRANSPARENT);
                        lastShedEditTextView.setEnabled(false);

                        stringInput = sexSpinner.getSelectedItem().toString().trim();
                        currentCard.setmSex(stringInput);
                        sexSpinner.setBackgroundColor(Color.TRANSPARENT);
                        sexSpinner.setEnabled(false);

                        stringInput = morphEditTextView.getText().toString();
                        currentCard.setmMorph(stringInput);
                        morphEditTextView.setText(stringInput);
                        morphEditTextView.setBackgroundColor(Color.TRANSPARENT);
                        morphEditTextView.setEnabled(false);






//                        // get image
//                        Bitmap petProfileImage;
//                        String encodedImage;
//                        if (gotImage) {
//                            // Get Bitmap of image
//                            petProfileImage = ((BitmapDrawable) profilePicture.getDrawable()).getBitmap();
//                            // compress image..not sure if this is necessary
//                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                            petProfileImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//                            encodedImage = android.util.Base64.encodeToString(byteArrayOutputStream.toByteArray(), android.util.Base64.DEFAULT);
//                        } else {
//                            // set petProfileImage to a default image
//                            // set petProfileImage to a default image....
//                            petProfileImage = BitmapFactory.decodeResource(getResources(), R.drawable.defaultpetimage);
//                        }

                        //Log.d(TAG,"petSex: " + petSex + " petName: " + petName + " petBday: " + petBdate.toString() + "petWeight: " + petWeight);
                        //Log.d(TAG,"gotImage: " + gotImage);


//                        if (petSex.equals("Male ♂")) {
//                            petSex = "Male ♂";
//                        } else if (petSex.equals("Female ♀")) {
//                            petSex = "Female ♀";
//                        }
//
//                        // data validation
//                        if (validateData()) {
//                            //toast up saying there are entry errors
//                            Toast.makeText(this, "Profile NOT updated. See errors above.", Toast.LENGTH_LONG).show();
//                        }
//
//                        //backButton.setEnabled(true);


                        buttonClicked = 1;
                    }
                    break;
                case R.id.backButton:
                    Intent intent = new Intent(pet_profile.this, home_page.class);
                    startActivity(intent);
                }
            }


    public boolean validateData()
    {
        boolean foundError = false;
        if(petName.isEmpty() || petName.length() > 16)
        {
            nameEditTextView.setError("Name cannot be empty or exceed 16 characters.");
            foundError = true;
        }

        if(tempDate.equals(""))
        {
            ageEditTextView.setError("Must enter a birth date.");
            foundError = true;
        }
        else
            ageEditTextView.setError(null);

        int integerPlaces = Double.toString(petWeight).indexOf(".");
        int decimalPlaces = Double.toString(petWeight).length() - integerPlaces - 1;
        // if integerPlaces == -1 then there is no decimal in the number...
        Log.d(TAG,"NUMBER OF INTEGER PLACES:: " + integerPlaces + "  number of decimal places:; " + decimalPlaces );

        if(petWeight == 0.0 || (decimalPlaces > 3  && integerPlaces != -1))
        {
            weightEditTextView.setError("Weight cannot be empty and no more than 3 decimal places (ex: 00.123)");
            foundError = true;
        }
        else
            weightEditTextView.setError(null);

        return foundError;
    }
}


