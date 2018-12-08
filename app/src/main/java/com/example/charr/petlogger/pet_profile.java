package com.example.charr.petlogger;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private EditText lastFedEditTextView;
    private EditText weightEditTextView;
    private EditText lastShedEditTextView;
    private EditText sexEditTextView;
    private EditText morphEditTextView;
    private de.hdodenhof.circleimageview.CircleImageView profilePicture;

    int buttonClicked = 1; // If the edit button has been clicked once (begin editing) or twice (finished editing)

    // variables to edit age of pet
    private DatePickerDialog.OnDateSetListener bdayDateSetListener;
    public String tempDate = "";
    public java.util.Date petBdate;


    private card_list cardList = card_list.getmInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile);

        profilePicture = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.profilePicture);
        profilePicture.setEnabled(false);

        backButton = (Button) findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainPage();
            }
        });


        // Initializes text boxes and sets disables them
        initializeEditTextViews();
        ageEditTextView.setOnClickListener(this);


        // Get specific card index
        card_item currentCard = getCurrentCard();

        // Display pet information in EditText fields
        displayPetInfo(currentCard);
    }

    public void openMainPage(){
        Intent intent = new Intent(this, home_page.class);
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

    public card_item getCurrentCard()
    {
        Intent cardIntent = getIntent();
        int index = cardIntent.getExtras().getInt("indexInArrayList");
        return cardList.getArray().get(index);
    }

    public void initializeEditTextViews()
    {
        nameEditTextView = (EditText) findViewById(R.id.nameEditTextView);
        nameEditTextView.setEnabled(false);

        ageEditTextView = (EditText) findViewById(R.id.ageEditTextView);
        ageEditTextView.setEnabled(false);

        lastFedEditTextView = (EditText) findViewById(R.id.lastFedEditTextView);
        lastFedEditTextView.setEnabled(false);

        weightEditTextView = (EditText) findViewById(R.id.weightEditTextView);
        weightEditTextView.setEnabled(false);

        lastShedEditTextView = (EditText) findViewById(R.id.lastShedEditTextView);
        lastShedEditTextView.setEnabled(false);

        sexEditTextView = (EditText) findViewById(R.id.sexEditTextView);
        sexEditTextView.setEnabled(false);

        morphEditTextView = (EditText) findViewById(R.id.morphEditTextView);
        morphEditTextView.setEnabled(false);
    }

    public void displayPetInfo(card_item currentCard)
    {
        nameEditTextView.setText(currentCard.getName());
        weightEditTextView.setText(Double.toString(currentCard.getCurrentWeight()));
        profilePicture.setImageBitmap(currentCard.getImage());
        ageEditTextView.setText(Integer.toString(currentCard.getAge(currentCard.getBirthDate())));
        lastShedEditTextView.setText(currentCard.dateObjectToMonthDayYearString(currentCard.getLastFed()));
        lastFedEditTextView.setText(currentCard.dateObjectToMonthDayYearString(currentCard.getLastFed()));
        sexEditTextView.setText(currentCard.getSex());
        morphEditTextView.setText(currentCard.getMorph());
    }

    // When the edit button is clicked
    public void editTextViews(View view) {
        // edit button clicked once (begin editing)
        if (buttonClicked == 1) {

            nameEditTextView.setBackgroundColor(Color.parseColor("#f7f6f2"));
            nameEditTextView.setCursorVisible(true);
            nameEditTextView.setFocusableInTouchMode(true);
            nameEditTextView.setInputType(InputType.TYPE_CLASS_TEXT);
            nameEditTextView.requestFocus(); //to trigger the soft input
            nameEditTextView.setEnabled(true);

            ageEditTextView.setBackgroundColor(Color.parseColor("#f7f6f2"));
            ageEditTextView.setCursorVisible(true);
            ageEditTextView.setFocusableInTouchMode(true);
            ageEditTextView.setInputType(InputType.TYPE_CLASS_TEXT);
            ageEditTextView.requestFocus(); //to trigger the soft input
            ageEditTextView.setEnabled(true);

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

            sexEditTextView.setBackgroundColor(Color.parseColor("#f7f6f2"));
            sexEditTextView.setCursorVisible(true);
            sexEditTextView.setFocusableInTouchMode(true);
            sexEditTextView.setInputType(InputType.TYPE_CLASS_TEXT);
            sexEditTextView.requestFocus(); //to trigger the soft input
            sexEditTextView.setEnabled(true);

            morphEditTextView.setBackgroundColor(Color.parseColor("#f7f6f2"));
            morphEditTextView.setCursorVisible(true);
            morphEditTextView.setFocusableInTouchMode(true);
            morphEditTextView.setInputType(InputType.TYPE_CLASS_TEXT);
            morphEditTextView.requestFocus(); //to trigger the soft input
            morphEditTextView.setEnabled(true);

            backButton.setEnabled(false);

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
                        ageEditTextView.setError(null);
                    ageEditTextView.setText(tempDate);
                }
            };


            buttonClicked = 0;
        }

        // edit button clicked twice (finished editing)
        else {
            card_item currentCard = getCurrentCard();
            String stringInput;
            double doubleInput;
            Date dateInput;


            stringInput = nameEditTextView.getText().toString();
            currentCard.setmName(stringInput);
            nameEditTextView.setText(stringInput);
            nameEditTextView.setBackgroundColor(Color.TRANSPARENT);
            nameEditTextView.setEnabled(false);

            stringInput = ageEditTextView.getText().toString();
            //currentCard.setmName(stringInput);
            ageEditTextView.setText(stringInput);
            ageEditTextView.setBackgroundColor(Color.TRANSPARENT);
            ageEditTextView.setEnabled(false);

            stringInput = lastFedEditTextView.getText().toString();
            //currentCard.setmName(stringInput);
            lastFedEditTextView.setText(stringInput);
            lastFedEditTextView.setBackgroundColor(Color.TRANSPARENT);
            lastFedEditTextView.setEnabled(false);

            doubleInput = Double.parseDouble(weightEditTextView.getText().toString());
            currentCard.setmWeight(doubleInput);
            weightEditTextView.setText(weightEditTextView.getText().toString());
            weightEditTextView.setBackgroundColor(Color.TRANSPARENT);
            weightEditTextView.setEnabled(false);

            stringInput = lastShedEditTextView.getText().toString();
            //currentCard.setmName(stringInput);
            lastShedEditTextView.setText(stringInput);
            lastShedEditTextView.setBackgroundColor(Color.TRANSPARENT);
            lastShedEditTextView.setEnabled(false);

            stringInput = sexEditTextView.getText().toString();
            currentCard.setmSex(stringInput);
            sexEditTextView.setText(stringInput);
            sexEditTextView.setBackgroundColor(Color.TRANSPARENT);
            sexEditTextView.setEnabled(false);

            stringInput = morphEditTextView.getText().toString();
            currentCard.setmMorph(stringInput);
            morphEditTextView.setText(stringInput);
            morphEditTextView.setBackgroundColor(Color.TRANSPARENT);
            morphEditTextView.setEnabled(false);

            backButton.setEnabled(true);


            buttonClicked = 1;
        }
    }


    // Insert switch statement for each text field
    @Override
    public void onClick(View v) {

        if (!backButton.isEnabled())
        {}

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
    }
}
