package com.example.charr.petlogger;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import java.util.Calendar;
import java.util.Date;

public class card_item extends AppCompatActivity {
    private int mImageResource;
    private String mName;
    private String mMorph;
    private String mSex;
    private String mBday;
    private double mInitialWeight;
    private Calendar mCalendar = Calendar.getInstance();
    private GraphView mGraph = (GraphView) findViewById(R.id.weight_graph);
    private LineGraphSeries<DataPoint> mWeights;
    private weight_log weightLog = new weight_log();

    public card_item(int imageResource, String name, String morph, String sex, double mInitialWeight)
    {
        mImageResource = imageResource;
        mName = name;
        mMorph = morph;
        mSex = sex;


        // Sets up graph and adds the initial weight
        formatGraph(mInitialWeight);
    }

    public int getImageResource() {return mImageResource;}

    public String getName() {return mName;}

    public String getMorph() {return mMorph;}

    public String getSex()
    {
        return mSex;
    }

    public double getInitialWeight() {return mInitialWeight;}

    public Calendar getCalendar() {return mCalendar;}

    public GraphView getGraph() {return mGraph;}

    public LineGraphSeries<DataPoint> getWeights() {return mWeights;}

    private void formatGraph(double initialWeight)
    {
        // Initial weight
        Date mCurrentDate = mCalendar.getTime();
        mWeights = new LineGraphSeries<>(new DataPoint[] {new DataPoint(mCurrentDate, mInitialWeight)});
        mGraph.addSeries(mWeights);
        mGraph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter());
        mGraph.getGridLabelRenderer().setNumHorizontalLabels(3);

        mGraph.getViewport().setMinX(mCurrentDate.getTime());
        mGraph.getViewport().setXAxisBoundsManual(true);

        mGraph.getGridLabelRenderer().setHumanRounding(false);
    }
}
