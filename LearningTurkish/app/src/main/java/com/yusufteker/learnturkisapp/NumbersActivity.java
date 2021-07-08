package com.yusufteker.learnturkisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("one","bir", R.drawable.number_one, R.raw.bir));
        words.add(new Word("two","iki", R.drawable.number_two, R.raw.iki));
        words.add(new Word("three","üç", R.drawable.number_three, R.raw.uc));
        words.add(new Word("four","dört", R.drawable.number_four, R.raw.dort));
        words.add(new Word("five","beş", R.drawable.number_five, R.raw.bes));
        words.add(new Word("six","altı", R.drawable.number_six, R.raw.alti));
        words.add(new Word("seven","yedi", R.drawable.number_seven, R.raw.yedi));
        words.add(new Word("eight","sekiz", R.drawable.number_eight, R.raw.sekiz));
        words.add(new Word("nine","dokuz", R.drawable.number_nine, R.raw.dokuz));
        words.add(new Word("ten","on", R.drawable.number_ten, R.raw.on));


       WordAdapter wordsAdapter = new WordAdapter(this, words, R.color.category_numbers);


        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


              mMediaPlayer = MediaPlayer.create(NumbersActivity.this, words.get(position).getResourceSoundId()  );
              mMediaPlayer.start();

           mMediaPlayer.setOnCompletionListener(mOnCompletionListener);


            }

        });

    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }


}