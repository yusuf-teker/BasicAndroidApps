package com.yusufteker.learnturkisapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int colorId;
    private MediaPlayer mp;

    public WordAdapter(Context context, ArrayList<Word> words, int colorId) {
        super(context, 0, words);
        this.colorId = colorId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        Word word = getItem(position);
        View list_item_view = convertView;

        if(list_item_view==null){
            list_item_view = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        TextView wordTr = list_item_view.findViewById(R.id.turkishWordTextView);
        TextView wordEn = list_item_view.findViewById(R.id.englishWordTextView);
        wordTr.setText(word.getWordTr());
        wordEn.setText(word.getWordEn());
        ImageView image = list_item_view.findViewById(R.id.imageViewId);



        if (!word.hasImage()){
            image.setVisibility(View.GONE);
        }else{

            image.setImageResource(word.getResourceImageId());
            image.setVisibility(View.VISIBLE);
        }

        View linearLayout = list_item_view.findViewById(R.id.linearLayout);
        linearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), colorId));

        return list_item_view;
    }

}
