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
import android.widget.EditText;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.Date;

public class weight_log extends AppCompatActivity implements View.OnClickListener {

    final String TAG = this.getClass().getName();

    private Calendar mCalendar = Calendar.getInstance();
    private card_list cardList = card_list.getmInstance();
    private card_item currentCard;

    private DatePickerDialog.OnDateSetListener fromEntryDateListener, toEntryDateListener;
    private TextView fromEntryDateTextView, toEntryDateTextView;
    private TextView petName;
    private String tempDate = "";
    private String tempDate2 = "";
    public java.util.Date fromDate = null, toDate = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_log);

        currentCard = getCurrentCard();
        currentCard.setmGraph((com.jjoe64.graphview.GraphView) findViewById(R.id.weight_graph));

        petName =(TextView) findViewById(R.id.petName);
        String title = currentCard.getName() + "'s Weight";
        petName.setText(title);

        formatGraph(currentCard.getWeight());

        fromEntryDateTextView = (TextView) findViewById(R.id.fromEntryDate);
        fromEntryDateTextView.setOnClickListener(this);

        toEntryDateTextView = (TextView) findViewById(R.id.toEntryDate);
        toEntryDateTextView.setOnClickListener(this);



        //set up DateSetListener
        fromEntryDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1; // starts at 0 for months...
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + dayOfMonth + "/" + year);

                tempDate = month + "/" + dayOfMonth + "/" + year;
                Calendar tempC = Calendar.getInstance();
                tempC.set(year, month - 1, dayOfMonth);
                fromDate = tempC.getTime();

                if (tempDate.length() > 0) // Error set needs to be in here
                    fromEntryDateTextView.setError(null);
                fromEntryDateTextView.setText(tempDate);
            }
        };

        //set up DateSetListener
        toEntryDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1; // starts at 0 for months...
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + dayOfMonth + "/" + year);

                tempDate = month + "/" + dayOfMonth + "/" + year;
                Calendar tempC = Calendar.getInstance();
                tempC.set(year, month - 1, dayOfMonth);
                toDate = tempC.getTime();

                if (tempDate2.length() > 0) // Error set needs to be in here
                    toEntryDateTextView.setError(null);
                toEntryDateTextView.setText(tempDate);
            }
        };

    }

    public void goToPetProfile(View view) {
        Intent intent = new Intent(view.getContext(), pet_profile.class);
        intent.putExtra("indexInArrayList", cardList.getIndex(currentCard));
        view.getContext().startActivity(intent);
    }

    public void addWeightEntry(View view) {
        Intent intent = new Intent(view.getContext(), weight_log_entry.class);
        intent.putExtra("indexInArrayList", cardList.getIndex(currentCard));
        view.getContext().startActivity(intent);
    }

    public card_item getCurrentCard() {
        Intent cardIntent = getIntent();
        int index = cardIntent.getExtras().getInt("indexInArrayList");
        return cardList.getArray().get(index);
    }

    private void formatGraph(double initialWeight) {
        // Initial weight

        Date mCurrentDate = mCalendar.getTime();
        currentCard.setmWeights(new LineGraphSeries<>(new DataPoint[]{new DataPoint(mCurrentDate, currentCard.getWeight())}));
        currentCard.getmGraph().addSeries(currentCard.getmWeights());
        currentCard.getmGraph().getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        currentCard.getmGraph().getGridLabelRenderer().setNumHorizontalLabels(1);
        //staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});

        currentCard.getmGraph().getViewport().setMinX(mCurrentDate.getTime());
        currentCard.getmGraph().getViewport().setXAxisBoundsManual(true);

        currentCard.getmGraph().getGridLabelRenderer().setHumanRounding(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fromEntryDate:
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        fromEntryDateListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog.show();
                break;
            case R.id.toEntryDate:
                Calendar cal2 = Calendar.getInstance();
                int year2 = cal2.get(Calendar.YEAR);
                int month2 = cal2.get(Calendar.MONTH);
                int day2 = cal2.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog2 = new DatePickerDialog(
                        this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        toEntryDateListener,
                        year2, month2, day2);

                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog2.show();
                break;
        }
    }
}
