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
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    //MediaPlayer
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
        words.add(new Word("What’s up?","Naber?",-1,R.raw.naber));
        words.add(new Word("Pretty good.","Oldukça iyi.",-1,R.raw.oldukcaiyi));
        words.add(new Word("I really appreciate it.","Gerçekten minnetarım.",-1,R.raw.gercektenminnetarim));
        words.add(new Word("No problem.","Sorun değil.",-1,R.raw.sorundegil));
        words.add(new Word("It was nice chatting with you.","Seninle sobet etmek güzeldi",-1,R.raw.seninlesohbetetmekguzeldi));
        words.add(new Word("I have no idea/clue.","Hiçbir fikrim yok.",-1,R.raw.hicbirfikrimyok));
        words.add(new Word("It doesn’t make any difference to me.","Benim için hiç fark etmez",-1,R.raw.benimicinhicfarketmez));
        words.add(new Word("I couldn’t agree with you more.","Sana aha fazla katılıyor olamazdım.",-1,R.raw.sanadahafazlakkatiliyorolamazdim));
        words.add(new Word(" I’m not so sure about that.","Bu konuda emin değilim.",-1,R.raw.bukonudaemindegilim));
        WordAdapter wordsAdapter = new WordAdapter(this, words, R.color.category_phrases);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, words.get(position).getResourceSoundId()  );
                if (audioFocusRequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer.start();
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