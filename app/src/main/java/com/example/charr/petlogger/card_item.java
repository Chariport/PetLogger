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

    private Bitmap mImage;
    private String mName;
    private Date mBdate;
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
        mMorph = "defaultMorph";
    }

    public Bitmap getImage() { return mImage; }

    public String getName() {return mName;}

    public String getMorph() {return mMorph;}

    public String getSex()
    {
        return mSex;
    }

    public Date getBirthDate(){return mBdate;}

    public int getAge(Date birthdate)
    {
        Calendar present = Calendar.getInstance();
        Calendar past = Calendar.getInstance();
        past.setTime(birthdate);

        int years = 0;

        while (past.before(present)) {
            past.add(Calendar.YEAR, 1);
            if (past.before(present)) {
                years++;
            }
        } return years;
    }
}
