package com.example.charr.petlogger;

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
    private ArrayList<card_item> mCardList;

    public static class CardViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView mImageView;
        public TextView mName, mMorph;

        public CardViewHolder(@NonNull View itemView)
        {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.name);
            mMorph = itemView.findViewById(R.id.morph);
        }
    }

    public card_adapter(ArrayList<card_item> cardList)
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
        card_item currItem = mCardList.get(i);

        cardViewHolder.mImageView.setImageResource(currItem.getImageResource());
        cardViewHolder.mName.setText(currItem.getName());
        cardViewHolder.mMorph.setText(currItem.getMorph());
    }

    @Override
    public int getItemCount()
    {
        return mCardList.size();
    }
}
