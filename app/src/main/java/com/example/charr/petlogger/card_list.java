package com.example.charr.petlogger;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.stream.Stream;

public class card_list extends ArrayList<Parcelable> {
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

    public void deleteFromArray(card_item card) { list.remove(card);}


    //indexs
    public int getIndex(card_item card){return list.indexOf(card);}
    public card_item getObjectAtIndex(int indexofobj) { return list.get(indexofobj);}//(list.indexOf(indexofobj)); }


    @Override
    public Stream<Parcelable> stream() {
        return null;
    }
}
