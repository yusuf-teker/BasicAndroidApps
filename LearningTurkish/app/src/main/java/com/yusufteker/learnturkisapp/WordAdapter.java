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

public class WordAdapter extends ArrayAdapter<Word> {//Word tipinde obje alan ArrayAdapteri extend ettik

    private int colorId;
    private MediaPlayer mp;
    /*
    * @param context            The current context. Used to inflate the layout file.
    * @param words              A List of Word objects to display in a list
    */
    public WordAdapter(Context context, ArrayList<Word> words, int colorId) {
        super(context, 0, words);
            //super burada extend edilen classın construcorı yerine geçiyor
            //O constructor 3 adet parametre istiyordu
            //bizim const. 2 adet paramatre alıyor. O yüzden 3.'yü 0 olarak verdim
        this.colorId = colorId;
    }

    //generic kisminden getView'i override ediyoruz !!!!
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) { //burasi hep böyle

        //(HER SEFERINDE 1 ADET WORD'U DATADAN ALIP ONUN UZERINDE ISLEM YAPICAZ !!! )
        Word word = getItem(position); // getItem Fonksiyonu(ArrayAdapter icinde var) Verilen Poisiton'daki elemani verir
                                        // Bu verileri WordAddapter'e olusturulurken gondermistik -> Word'lerden olusan bir ArrayList olarak gondermistik

        //(EN SON RETURN EDILECEK CUSTOM VIEW'IMIZ)
        View list_item_view = convertView;  //ReUse edilecelek Viewler
            //ReUse edilecek View'lerin listView'lar oldugunu bildigimiz icin ismini list_item_view koyduk

        //(XML DOSYASINI VIEW'E DONUSTURUYORUZ)
        if(list_item_view==null){
            list_item_view = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
                //Eger null ise list_item XML dosyasindaki tum list item Viewleri inflate eder
                //LayoutInflater: XML Layout Dosyasini gercek bir View Objesine donusturur

                //Burada View'i manuel olarak inflate ediyoruz. Bu yüzden WordAddapter'da sadece 2 arguman alıp View Layoutunu almamıstık
        }


        //(LIST ITEM'ımımın ICINDEKI VIEWLARI DOGRU POSITIONDAKI WORD'un BILGILERI ILE DOLDURUYORUZ)
        TextView wordTr = list_item_view.findViewById(R.id.turkishWordTextView);//Kendi Olusturdugumuz viewdeki 1. String (TextView)
        TextView wordEn = list_item_view.findViewById(R.id.englishWordTextView);//Kendi Olusturdugumuz viewdeki 2. String (TextView)
        wordTr.setText(word.getWordTr());
        wordEn.setText(word.getWordEn());
        ImageView image = list_item_view.findViewById(R.id.imageViewId);



        if (!word.hasImage()){
            image.setVisibility(View.GONE);
        }else{

            image.setImageResource(word.getResourceImageId()); //gösterilcek sıradaki WORD'un id'sini buna ayarlıyoruyz
            image.setVisibility(View.VISIBLE); // recyle edilince GONE olur onu düzelyitorz
        }

        // Set the theme color for the list item
        View linearLayout = list_item_view.findViewById(R.id.linearLayout);
        linearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), colorId));



        //(SEKILLENDIRDIGIMIZ VIEW'I YOLLUYORUZ)
        return list_item_view;
    }

}
