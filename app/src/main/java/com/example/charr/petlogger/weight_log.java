package com.example.charr.petlogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.Date;

public class weight_log extends AppCompatActivity {

    private Calendar mCalendar = Calendar.getInstance();
    private com.jjoe64.graphview.GraphView mGraph;
    private card_list cardList = card_list.getmInstance();
    private card_item currentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_log);

        mGraph = (com.jjoe64.graphview.GraphView) findViewById(R.id.weight_graph);
        currentCard = getCurrentCard();
        formatGraph(currentCard.getWeight());

    }

    public void goToPetProfile(View view)
    {
        Intent intent = new Intent(view.getContext(), pet_profile.class);
        intent.putExtra("indexInArrayList", cardList.getIndex(currentCard));
        view.getContext().startActivity(intent);
    }

    public void addWeightEntry(View view)
    {
        Intent intent = new Intent(view.getContext(), weight_log_entry.class);
        intent.putExtra("indexInArrayList", cardList.getIndex(currentCard));
        view.getContext().startActivity(intent);
    }

    public card_item getCurrentCard() {
        Intent cardIntent = getIntent();
        int index = cardIntent.getExtras().getInt("indexInArrayList");
        return cardList.getArray().get(index);
    }

    private void formatGraph(double initialWeight)
    {
        // Initial weight

        Date mCurrentDate = mCalendar.getTime();
        currentCard.setmWeights(new LineGraphSeries<>(new DataPoint[] {new DataPoint(mCurrentDate, currentCard.getWeight())}));
        mGraph.addSeries(currentCard.getmWeights());
        mGraph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        mGraph.getGridLabelRenderer().setNumHorizontalLabels(1);

        mGraph.getViewport().setMinX(mCurrentDate.getTime());
        mGraph.getViewport().setXAxisBoundsManual(true);

        mGraph.getGridLabelRenderer().setHumanRounding(false);
    }
}
