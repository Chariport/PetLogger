package com.example.charr.petlogger;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class card_adapter extends RecyclerView.Adapter<card_adapter.CardViewHolder>
{
    final String TAG = this.getClass().getName();

    private static card_list mCardList;
    private String sex;

    public static class CardViewHolder extends RecyclerView.ViewHolder
    {
        final String TAG = this.getClass().getName();

        public card_item currentCard;
        public View view;

        //public ImageView mImageView;
        public  ImageView mImageView;
        public TextView mName, mMorph, mSex, mAge, mWeight, mLastFed, numbering;


        public CardViewHolder(@NonNull final View itemView)
        {
            super(itemView);

            view = itemView;

            mImageView = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.nameTextView);
            mMorph = itemView.findViewById(R.id.morphValue);
            mSex =  itemView.findViewById(R.id.sex);
            mAge = itemView.findViewById(R.id.petsAgeValue);
            mWeight = itemView.findViewById(R.id.currentWeightValue);
            mLastFed = itemView.findViewById(R.id.lastFedValue);
            numbering = itemView.findViewById(R.id.numbering);


            // set onclick listener for entire card to detailed prof
            view.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View v)
                {
                    // item clicked
                    Log.d(TAG,"clicked on " + currentCard.getName());
                    Log.d(TAG,"clicked on index of " + Integer.toString(mCardList.getIndex(currentCard)));

                    // should now go to detailed prof page
                    // like this
                    /*
                        Intent intent = new Intent(view.getContext(), detaildedProfClassname.class);
                        intent.putExtra('indexInArrayList', Integer.toString(mCardList.getIndex(currentCard)));
                        view.getContext().startActivity(intent);

                        something close to above
                        then on detailedProfClassname java
                            - getInstance of the arrayList
                            - get the .putExtra by .getIntent()..(easy to look up)
                            - using that index you now have access to the objects info
                     */

                    Intent intent = new Intent(view.getContext(), pet_profile.class);
                    intent.putExtra("indexInArrayList", mCardList.getIndex(currentCard));
                    view.getContext().startActivity(intent);
                }
            });

            // onLong click listener for entire card to delete this item
            view.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    Log.d(TAG,"long press on " + currentCard.getName());

                    // set up delete alert pop-up
                    buildDeleteAlert(currentCard, view);

                    return true;
                }
            });
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
        cardViewHolder.currentCard = mCardList.getArray().get(i);


        cardViewHolder.mImageView.setImageBitmap(cardViewHolder.currentCard.getImage());
        cardViewHolder.mName.setText(cardViewHolder.currentCard.getName());
        cardViewHolder.mMorph.setText(cardViewHolder.currentCard.getMorph());
        cardViewHolder.mSex.setText(cardViewHolder.currentCard.getSex());
        cardViewHolder.mAge.setText(Integer.toString(cardViewHolder.currentCard.getAge(cardViewHolder.currentCard.getBirthDate())));
        cardViewHolder.mWeight.setText(Double.toString(cardViewHolder.currentCard.getWeight()) + " g");
        cardViewHolder.mLastFed.setText(cardViewHolder.currentCard.dateObjectToMonthDayYearString(cardViewHolder.currentCard.getLastFed()));
        cardViewHolder.numbering.setText(Integer.toString(i + 1) + "/" + Integer.toString(mCardList.getArray().size()));

        sex = cardViewHolder.currentCard.getSex();
        if (sex.equals("Male ♂"))
            cardViewHolder.mSex.setText("♂");
        else if (sex.equals("Female ♀"))
            cardViewHolder.mSex.setText("♀");
        else
            cardViewHolder.mSex.setText("");
    }

    @Override
    public int getItemCount()
    {
        return mCardList.getArray().size();
    }

    public static void buildDeleteAlert(final card_item currentCard, final View view)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(view.getContext(), R.style.AlertDialogCustom));//view.getContext());
        builder.setTitle("Delete " + currentCard.getName() + "?");

        //alert  Yes button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(view.getContext(), currentCard.getName() + " was deleted", Toast.LENGTH_LONG).show();
                mCardList.deleteFromArray(mCardList.getObjectAtIndex(mCardList.getIndex(currentCard)));

                // 'refresh home page' ...want to just recall buildRecyclerView() but don't know how
                Intent i = new Intent(view.getContext(),home_page.class);
                view.getContext().startActivity(i);
            }
        });

        //alert No button
        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        AlertDialog diag = builder.create();
        diag.show();
    }
}
