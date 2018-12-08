package com.example.charr.petlogger;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;


public class card_item extends AppCompatActivity {
    final String TAG = this.getClass().getName();

    private String mMorph;  //not used yet ..set to default in constructor

    //we need to add some data structure here for feeding..

    private Bitmap mImage;
    private String mName;
    private Date mBdate;
    private Date mLastFed;
    private String mSex;
    private double mWeight;


    public card_item(Bitmap image, String name, Date date, String sex, double weight)
    {
        mImage = image;
        mName = name;
        mBdate = date;
        mSex = sex;
        mWeight = weight;

        //set others to default
        mLastFed = new Date(); // day of creation
        mMorph = "----"; // should be able to change on detailed profile
    }

    public Bitmap getImage() { return mImage; }

    public String getName() {return mName;}

    public String getMorph() {return mMorph;}

    public String getSex()
    {
        return mSex;
    }

    public Date getBirthDate(){return mBdate;}

    public Date getLastFed() {return mLastFed;}

    public void setMorph(String m)
    {
        this.mMorph = m;
    }

    public void setmMorph(String mMorph) { this.mMorph = mMorph;}

    public void setmImage(Bitmap mImage) {this.mImage = mImage;}

    public void setmName(String mName) {this.mName = mName;}

    public void setmBdate(Date mBdate) {this.mBdate = mBdate;}

    public void setmLastFed(Date mLastFed) {this.mLastFed = mLastFed;}

    public void setmSex(String mSex) {this.mSex = mSex;}

    public void setmWeight(double mWeight) {this.mWeight = mWeight;}

    public String dateObjectToMonthDayYearString(Date dd)
    {
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(dd);
        // wanted format: month/day/year
        String month = Integer.toString(tempCalendar.get(Calendar.MONTH) + 1);// off by one indexing
        String day = Integer.toString(tempCalendar.get(Calendar.DAY_OF_MONTH));
        String year = Integer.toString(tempCalendar.get(Calendar.YEAR));
        return month+"/"+day+"/"+year;
    }

    public double getCurrentWeight()
    {
        // get the most recent entered weight..however we are storing it


        // for now its just the entered weight on create.
        return this.mWeight;
    }

    public int getAge(Date birthdate)
    {
        Calendar present = Calendar.getInstance();
        Calendar past = Calendar.getInstance();
        past.setTime(birthdate);

        int years = 0;

        while (past.before(present))
        {
            past.add(Calendar.YEAR, 1);
            if (past.before(present))
            {
                years++;
            }
        }

        return years;
    }
}
