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
    //Sıkca Kullanılacagı icin Global Olarak tanımladık , defalarca new yapmak yerine her zaman bunu kullanacagız
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
/*
        //Words
        ArrayList<String> wordsEn = new ArrayList<String>(
                Arrays.asList(new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"}));
        //WordsInTurkish
        ArrayList<String> wordsTr = new ArrayList<String>(
                Arrays.asList(new String[] {"bir", "iki", "üç", "dört", "beş", "altı", "yedi", "sekiz", "dokuz", "on"}));
*/
    // Yeni bir object or Words Objesi olusturup 2 listeyide gonderebilriz
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





        // KENDI OLUSTURDUGUMUZ LAYOUTU KULLANCAZ list_item.xml
        // ArrayAdapter 2.Parametre olarak basit bir TextView istiyor ama bizimki LinearLayout icinde 2 TextView'in
        // Birlesiminden olusuyor yani basit bir TextView degil.

        //Bu yüzden Bizim istedigimiz View tarzında olacak sekilde ADAPTER'ida KENDIMIZ OLUSTURMAMIZ GEREKIYOR
        //Yine bir class olusturuyoruz WordAdapter extend ArrayAdapter
            // ve getView Metodunu override ederek kendi karmasik  Layoutumuza (View'imize) uygun sekile getiriyoruz

        //(KENDI ADAPTERIMIZ)
       WordAdapter wordsAdapter = new WordAdapter(this, words, R.color.category_numbers); // https://gelecegiyazanlar.turkcell.com.tr/konu/egitim/android-201/listview-ozellestirme
            //Burada ek olarak Turkce kelimelerinide gondermek istiyoruz o yuzden ArrayAdapterin documentationina bakiyorum
            //Fakat 2 adet farkli listeyi arguman olarak alan bir constructor yok ama 3.ARGUMAN BIR OBJECT ISTIYOR (LIST OLMASINA GEREK YOK) o yüzden Word clası olusturup onu kullandık



        //(LISTVIEW - ADAPTER arasindaki  ILISKIYI KURMA)
        ListView listView = findViewById(R.id.list); // ListView'i activity,numbers icinde olusturmustuk
        listView.setAdapter(wordsAdapter); //Bu ListView'e olusturdugumuz Adapteri entegre ediyoruz

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

              mMediaPlayer = MediaPlayer.create(NumbersActivity.this, words.get(position).getResourceSoundId()  );
              mMediaPlayer.start();

              /* Her Seferinde new OnCompletionListern yapmak yerine 1 kez global olarak tanımlayıp sürekli onu kullanabiliriz
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        // Do something when media player end playing
                        Toast.makeText(NumbersActivity.this,"Media Player End.",Toast.LENGTH_SHORT).show();
                        releaseMediaPlayer();
                    }
                });*/

                // MEDIA PLAYER'A OZEL  OnCompletionListener'imizi Eklemek
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);


            }

        });

    }


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }



}