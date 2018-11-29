package com.example.charr.petlogger;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class card_adapter extends RecyclerView.Adapter<card_adapter.CardViewHolder>
{
    final String TAG = this.getClass().getName();

    public static card_list mCardList;
    public static Context context;


    public static class CardViewHolder extends RecyclerView.ViewHolder
    {
        final String TAG = this.getClass().getName();

        public card_item currentCard;
        public View view;

        //public ImageView mImageView;
        public  ImageView mImageView;
        public TextView mName, mMorph, mSex, mAge;



        public CardViewHolder(@NonNull final View itemView)
        {
            super(itemView);

            view = itemView;


            mImageView = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.name);
            mMorph = itemView.findViewById(R.id.morph);
            mSex =  itemView.findViewById(R.id.sex);
            mAge = itemView.findViewById(R.id.petsAge);

            // set onclick listener to detailed prof
            view.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View v)
                {
                    // item clicked
                    Log.d(TAG,"clicked on " + currentCard.getName());
                    Log.d(TAG,"clicked on index of " + Integer.toString(mCardList.getIndex(currentCard)));
                }
            });

            // onLong click listener to delete this item
            view.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    Log.d(TAG,"long press on " + currentCard.getName());

                    Intent intent = new Intent(view.getContext(),activity_delete.class);
                    intent.putExtra("indexToDelete", mCardList.getIndex(currentCard));

                    Log.d(TAG,"getContext of view: " + view.getContext().toString());
                    Log.d(TAG,"index to get passed to deleteview :" + Integer.toString(mCardList.getIndex(currentCard)));

                    view.getContext().startActivity(intent);


                    return true;//whats this here for?
                }
            });
        }
    }

    public card_adapter(card_list cardList)
    {
        //this.context = context;
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
        //changed this
        cardViewHolder.currentCard = mCardList.getArray().get(i);

        //cardViewHolder.mImageView.setImageResource(currItem.getImageResource());
        cardViewHolder.mImageView.setImageBitmap(cardViewHolder.currentCard.getImage());
        cardViewHolder.mName.setText(cardViewHolder.currentCard.getName());
        cardViewHolder.mMorph.setText(cardViewHolder.currentCard.getMorph());
        cardViewHolder.mSex.setText(cardViewHolder.currentCard.getSex());
        cardViewHolder.mAge.setText(Integer.toString(cardViewHolder.currentCard.getAge(cardViewHolder.currentCard.getBirthDate())));
        // need to add getAge method in card_item that converts the bdate to age based on todays date
    }

    @Override
    public int getItemCount()
    {
        return mCardList.getArray().size();
    }
}
