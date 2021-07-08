package com.yusufteker.learnturkisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("mother","anne", R.drawable.family_mother));
        words.add(new Word("father","baba", R.drawable.family_father));
        words.add(new Word("sister","kız kardeş", R.drawable.family_younger_sister));
        words.add(new Word("brother","erkek kardeş", R.drawable.family_younger_brother));
        words.add(new Word("grandfather","dede", R.drawable.family_grandfather));
        words.add(new Word("grandmother","nine", R.drawable.family_grandmother));
        words.add(new Word("son","oğul", R.drawable.family_son));
        words.add(new Word("daughter","kız", R.drawable.family_daughter));
        WordAdapter wordsAdapter = new WordAdapter(this, words, R.color.category_family);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordsAdapter);










    }
}