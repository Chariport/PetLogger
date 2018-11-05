package com.example.charr.petlogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class petCardMainPage extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.activity_pet_card_main_page);

        ArrayList<CardItem> cardList = new ArrayList<>();
        cardList.add(new CardItem(R.drawable.snake2, "Kenny", "Piebald"));
        cardList.add(new CardItem(R.drawable.snake, "Sagay", "CoralGLow Clown"));
        cardList.add(new CardItem(R.drawable.snake3, "Maddie", "Firefly het Piebald"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CardAdapter(cardList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
