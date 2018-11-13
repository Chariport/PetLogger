package com.example.charr.petlogger;

import android.support.v7.app.AppCompatActivity;

public class card_item extends AppCompatActivity {
    private int mindexInList;
    private int mImageResource;
    private String mName;
    private String mMorph;

    public card_item(int imageResource, String name, String morph)
    {
        //mindexInList = 0;       //always place new cards at the front of the list
        mImageResource = imageResource;
        mName = name;
        mMorph = morph;
    }

    public int getImageResource() {return mImageResource;}
    public String getName() {return mName;}
    public String getMorph() {return mMorph;}
}
