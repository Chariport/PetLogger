package com.example.charr.petlogger;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class pet_profile extends AppCompatActivity {
    private Button backButton;
    private Button weightLogButton;

    private TextView nameTextView;
    private TextView ageTextView;
    private TextView lastFedTextView;
    private TextView weightTextView;
    private TextView lastShedTextView;
    private TextView sexTextView;
    private ImageView profilePicture;

    private card_list cardList = card_list.getmInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile);

        profilePicture = (ImageView) findViewById(R.id.profilePicture);
        profilePicture.setEnabled(false);

        backButton = (Button) findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainPage();
            }
        });


        nameTextView = (TextView) findViewById(R.id.nameEditTextView);
        //nameTextView.setEnabled(false);

        ageTextView = (TextView) findViewById(R.id.ageEditTextView);
        //ageTextView.setEnabled(false);

        lastFedTextView = (TextView) findViewById(R.id.lastFedEditTextView);
        //lastFedTextView.setEnabled(false);

        weightTextView = (TextView) findViewById(R.id.weightEditTextView);
        //weightTextView.setEnabled(false);

        lastShedTextView = (TextView) findViewById(R.id.lastShedEditTextView);
        //lastShedTextView.setEnabled(false);

        sexTextView = (TextView) findViewById(R.id.sexEditTextView);
        //sexTextView.setEnabled(false);

        Intent cardIntent = getIntent();
        int index = cardIntent.getExtras().getInt("indexInArrayList");
        card_item currentCard = cardList.getArray().get(index);


        nameTextView.setText(currentCard.getName());
        weightTextView.setText(Double.toString(currentCard.getCurrentWeight()));
        profilePicture.setImageBitmap(currentCard.getImage());
        ageTextView.setText(Integer.toString(currentCard.getAge(currentCard.getBirthDate())));
        lastShedTextView.setText(currentCard.getLastFed().toString());
        lastFedTextView.setText(currentCard.getLastFed().toString());
        sexTextView.setText(currentCard.getSex());

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
}
