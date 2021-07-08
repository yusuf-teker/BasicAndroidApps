package com.yusufteker.learnturkisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_word_list);
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("red","kırmızı", R.drawable.color_red));
        words.add(new Word("dusty yellow","tozlu sarı", R.drawable.color_dusty_yellow));
        words.add(new Word("green","yeşil", R.drawable.color_green));
        words.add(new Word("brown","kahverengi", R.drawable.color_brown));
        words.add(new Word("black","siyah", R.drawable.color_black));
        words.add(new Word("mustard yellow","hardal sarısı", R.drawable.color_mustard_yellow));
        words.add(new Word("gray","gri", R.drawable.color_gray));
        words.add(new Word("white","beyaz", R.drawable.color_white));

        WordAdapter wordsAdapter = new WordAdapter(this, words, R.color.category_colors); // https://gelecegiyazanlar.turkcell.com.tr/konu/egitim/android-201/listview-ozellestirme
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordsAdapter);

    }
}