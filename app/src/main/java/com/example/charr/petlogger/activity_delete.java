package com.example.charr.petlogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_delete extends AppCompatActivity
{
    final String TAG = this.getClass().getName();

    private card_list cardList = card_list.getmInstance();


    public Button cancel, ok;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
    }

    public void clickedCancelButton(View view)
    {
        Intent intent = new Intent(activity_delete.this, home_page.class);
        startActivity(intent);
    }

    public void clickedOkButton(View view)
    {
        int indextodel = getIntent().getExtras().getInt("indexToDelete");
        cardList.deleteFromArray(cardList.getObjectAtIndex(indextodel));
        Intent intent = new Intent(activity_delete.this, home_page.class);
        startActivity(intent);
    }
}
