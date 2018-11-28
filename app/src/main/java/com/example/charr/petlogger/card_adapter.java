package com.example.charr.petlogger;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class card_adapter extends RecyclerView.Adapter<card_adapter.CardViewHolder>
{
    final String TAG = this.getClass().getName();

    private card_list mCardList;

    public static class CardViewHolder extends RecyclerView.ViewHolder
    {
        //public ImageView mImageView;
        public  ImageView mImageView;
        public TextView mName, mMorph, mSex, mAge;

        public CardViewHolder(@NonNull View itemView)
        {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.name);
            mMorph = itemView.findViewById(R.id.morph);
            mSex =  itemView.findViewById(R.id.sex);
            mAge = itemView.findViewById(R.id.petsAge);
        }
    }

    public card_adapter(card_list cardList)
    {
        mCardList = cardList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_item, viewGroup, false);

        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int i)
    {
        card_item currItem = mCardList.getArray().get(i);

        //cardViewHolder.mImageView.setImageResource(currItem.getImageResource());
        cardViewHolder.mImageView.setImageBitmap(currItem.getImage());
        cardViewHolder.mName.setText(currItem.getName());
        cardViewHolder.mMorph.setText(currItem.getMorph());
        cardViewHolder.mSex.setText(currItem.getSex());
        cardViewHolder.mAge.setText(Integer.toString(currItem.getAge(currItem.getBirthDate())));
        // need to add getAge method in card_item that converts the bdate to age based on todays date
    }

    @Override
    public int getItemCount()
    {
        return mCardList.getArray().size();
    }
}
