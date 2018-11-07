package com.example.charr.petlogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class home_page extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.activity_home_page);

        ArrayList<card_item> cardList = new ArrayList<>();
        cardList.add(new card_item(R.drawable.snake2, "Kenny", "Piebald"));
        cardList.add(new card_item(R.drawable.snake, "Sagay", "CoralGLow Clown"));
        cardList.add(new card_item(R.drawable.snake3, "Maddie", "Firefly het Piebald"));
        cardList.add(new card_item(R.drawable.snake4, "snek4", "Blade Clown"));
        cardList.add(new card_item(R.drawable.snake5, "snek5", "Leopard Clown"));
        cardList.add(new card_item(R.drawable.snake6, "snek6", "Pastel Clown"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new card_adapter(cardList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
