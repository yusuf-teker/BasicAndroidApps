package com.yusufteker.learnturkisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
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

    //AudioFocus
    private AudioManager mAudioManager;
    AudioAttributes playbackAttributes;
    AudioFocusRequest focusRequest;
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                mMediaPlayer.release();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);


        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        playbackAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        focusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(playbackAttributes)
                .setAcceptsDelayedFocusGain(true)
                .setOnAudioFocusChangeListener(audioFocusChangeListener)
                .build();
        final int audioFocusRequest = mAudioManager.requestAudioFocus(focusRequest);

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

              releaseMediaPlayer();
              mMediaPlayer = MediaPlayer.create(NumbersActivity.this, words.get(position).getResourceSoundId()  );
                if (audioFocusRequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer.start(); /***/
                }

              mMediaPlayer.setOnCompletionListener(mOnCompletionListener);

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocusRequest(focusRequest);
        }
    }


}