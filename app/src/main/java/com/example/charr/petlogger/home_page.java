package com.example.charr.petlogger;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

public class home_page extends AppCompatActivity {
    static Activity activityA;

    private ArrayList<card_item> mCardList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        activityA = this;

        createCardList();
        buildRecyclerView();
    }

    public static Activity getHomeInstance()
    {
        return activityA;
    }

    public void insertCard(card_item newCard)
    {
        mCardList.add(newCard);
    }

    public void removeCard()
    {

    }

    public void createCardList()
    {
        mCardList = new ArrayList<>();
//        mCardList.add(new card_item(R.drawable.snake2, "Kenny", "Piebald"));
//        mCardList.add(new card_item(R.drawable.snake, "Sagay", "CoralGLow Clown"));
//        mCardList.add(new card_item(R.drawable.snake3, "Maddie", "Firefly het Piebald"));
//        cardList.add(new card_item(R.drawable.snake4, "snek4", "Blade Clown"));
//        cardList.add(new card_item(R.drawable.snake5, "snek5", "Leopard Clown"));
//        cardList.add(new card_item(R.drawable.snake6, "snek6", "Pastel Clown"));

    }

    public void buildRecyclerView()
    {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new card_adapter(mCardList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void createPet(View v)
    {
        startActivity(new Intent(home_page.this, create_pet.class));
    }
}
