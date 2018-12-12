package com.example.charr.petlogger;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.jjoe64.graphview.series.DataPoint;

import java.util.Calendar;
import java.util.Date;

public class weight_log_entry extends AppCompatActivity implements View.OnClickListener{

    final String TAG = this.getClass().getName();

    private card_list cardList = card_list.getmInstance();
    private card_item currentCard;
    private Calendar mCalendar = Calendar.getInstance();
    private TextView weight;
    private TextView petNameTextView;

    private TextView date;
    public DatePickerDialog.OnDateSetListener DateSetListener;
    public java.util.Date weightDate;
    public String tempDate = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_log_entry);

        currentCard = getCurrentCard();
        weight = (TextView) findViewById(R.id.edittextWeight);

        date = (TextView) findViewById(R.id.date);
        date.setOnClickListener(this);

        petNameTextView =(TextView) findViewById(R.id.petNameTextView);
        String title = currentCard.getName() + "'s New Weight";
        petNameTextView.setText(title);

        //set up DateSetListener
        DateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                month += 1; // starts at 0 for months...
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + dayOfMonth + "/" + year);

                tempDate =  month + "/" + dayOfMonth + "/" + year;
                Calendar tempC = Calendar.getInstance();
                tempC.set(year, month - 1, dayOfMonth);
                weightDate = tempC.getTime();

                if(tempDate.length() > 0) // Error set needs to be in here
                    date.setError(null);
                date.setText(tempDate);
            }
        };
    }


    public void saveBackToWeightLog(View view)
    {
        double newWeight = Double.parseDouble(weight.getText().toString());
        currentCard.getmWeights().appendData(new DataPoint(weightDate, newWeight), true, 2);
        currentCard.getmGraph().addSeries(currentCard.getmWeights());

        pushExtras(view);
    }

    public void cancelBackToWeightLog(View view)
    {
        pushExtras(view);
    }

    public void pushExtras(View view)
    {
        Intent intent = new Intent(view.getContext(), weight_log.class);
        intent.putExtra("indexInArrayList", cardList.getIndex(currentCard));
        view.getContext().startActivity(intent);
    }


    public card_item getCurrentCard() {
        Intent cardIntent = getIntent();
        int index = cardIntent.getExtras().getInt("indexInArrayList");
        return cardList.getArray().get(index);
    }

    @Override
    public void onClick(View v) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DateSetListener,
                year, month, day);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        dialog.show();
    }
}
