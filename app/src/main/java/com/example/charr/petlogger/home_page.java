package com.example.charr.petlogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class home_page extends AppCompatActivity
{
    final String TAG = this.getClass().getName();

    private card_list cardList = card_list.getmInstance();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        if(savedInstanceState == null || !savedInstanceState.containsKey("cardList")) {
        buildRecyclerView();
        }
        else {
            cardList= (card_list) savedInstanceState.getParcelableArrayList("cardList");
        }
    }

    public void buildRecyclerView()
    {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new card_adapter(cardList);


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void fromHomePageToCreatePet(View view)
    {
        Intent intent = new Intent(home_page.this, create_pet.class);
        Log.d(TAG,"get context on homepage: " + home_page.this);
        startActivity(intent);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putParcelableArrayList("cardList",cardList);
        super.onSaveInstanceState(outState);
    }


}
