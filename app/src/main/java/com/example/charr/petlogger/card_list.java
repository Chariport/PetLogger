package com.example.charr.petlogger;

import java.util.ArrayList;

public class card_list {
    private static card_list mInstance;
    private ArrayList<card_item> list = null;

    private  card_list()
    {
        list = new ArrayList<card_item>();
    }

    public static card_list getmInstance()
    {
        if(mInstance == null)
        {
            mInstance = new card_list();
        }

        return mInstance;
    }

    public ArrayList<card_item> getArray()
    {
        return this.list;
    }

    public void addToArray(card_item card)
    {
        list.add(card);
    }

}
