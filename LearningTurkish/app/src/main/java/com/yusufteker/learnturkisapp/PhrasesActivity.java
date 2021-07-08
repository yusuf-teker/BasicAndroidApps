package com.yusufteker.learnturkisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("What’s up?","Naber?"));
        words.add(new Word("Pretty good.","Oldukça iyi."));
        words.add(new Word("I really appreciate it.","Gerçekten minnetarım."));
        words.add(new Word("No problem.","Sorun değil."));
        words.add(new Word("It was nice chatting with you.","Seninle sobet etmek güzeldi"));
        words.add(new Word("Do you have any idea…?","Bir fikrin var mı...?"));
        words.add(new Word("I have no idea/clue.","Hiçbir fikrim yok."));
        words.add(new Word("It doesn’t make any difference to me.","Benim için hiç fark etmez"));
        words.add(new Word("I couldn’t agree with you more.","Sana aha fazla katılıyor olamazdım."));
        words.add(new Word(" I’m not so sure about that.","Bu konuda emin değilim."));
        WordAdapter wordsAdapter = new WordAdapter(this, words, R.color.category_phrases);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordsAdapter);

    }
}