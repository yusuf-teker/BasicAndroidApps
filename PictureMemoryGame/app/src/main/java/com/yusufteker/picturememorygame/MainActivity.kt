package com.yusufteker.picturememorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock

import android.widget.ImageButton
import android.widget.Toast
import com.yusufteker.picturememorygame.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cards:List<CardView>
    private lateinit var  imageButtons: List<ImageButton>
    private lateinit var imagesId: MutableList<Int>
    private  var lastOpenCardIndex = -1
    private  var control = -1
    private  var isGameStarted = false
    private  var oldOpenCardIndex = listOf<Int>(-1,-1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //imagesId -> add DATA
        imagesId= mutableListOf(R.drawable.carousel,R.drawable.coffe,R.drawable.crownanemone,
        R.drawable.octopus,R.drawable.owl,R.drawable.westernstripedalbatros,R.drawable.carousel,R.drawable.coffe,R.drawable.crownanemone,
            R.drawable.octopus,R.drawable.owl,R.drawable.westernstripedalbatros)

        //imagesButtons -> add DATA
        imageButtons= listOf(binding.imageView1,binding.imageView2,binding.imageView3,binding.imageView4,binding.imageView5,
            binding.imageView6,binding.imageView7,binding.imageView8,binding.imageView9,binding.imageView10,
            binding.imageView11,binding.imageView12)

        shuffleCard()

        imageButtons.forEachIndexed { index, imageButton ->
            imageButton.setOnClickListener{
                if (!cards[index].isMatch && isGameStarted){
                    imageButtons[index].setImageResource(imagesId[index] )
                    cards[index].isOpen= true
                    //Update Models
                    updateModels(index)
                    //Update UI
                    updateViews()
                }
            }
        }

        binding.buttonStart.setOnClickListener {
            restartGame()

        }

    }


    //update model
    private fun updateModels(position: Int) {

        if (lastOpenCardIndex == -1){
            if (control == 1){
                cards[oldOpenCardIndex[0]].isOpen = false
                cards[oldOpenCardIndex[1]].isOpen = false
                control = -1
            }
            lastOpenCardIndex = position
            cards[lastOpenCardIndex].isOpen = true
            imageButtons[position].setClickable(false)
        }else if (cards[position].imagesId == cards[lastOpenCardIndex].imagesId){
            cards[position].isMatch = true
            cards[lastOpenCardIndex].isMatch= true
            cards[position].isOpen = true
            cards[lastOpenCardIndex].isOpen = true
            imageButtons[lastOpenCardIndex].setClickable(true)
            lastOpenCardIndex = -1
            /** When all the cards are MATCHED */
            if (isAllMatch()){
                binding.simpleChronometer.stop ()
                val time =  (SystemClock.elapsedRealtime() - binding.simpleChronometer.getBase())/1000
                val score =  if(time < 15) 100 else if(time > 15) 0 else 15 - time
                Toast.makeText(this@MainActivity, "Congratulations.. Your Score: $score ", Toast.LENGTH_LONG).show()
                binding.simpleChronometer.setBase(SystemClock.elapsedRealtime())

            }


        }else{
            imageButtons[lastOpenCardIndex].setClickable(true)
            oldOpenCardIndex = listOf(position,lastOpenCardIndex)
            lastOpenCardIndex = -1
            control = 1
        }
    }

    //update view if card is open then show the image else show the card's back
    private fun updateViews() {
        //Update all views !
        cards.forEachIndexed{index, card ->
            val button = imageButtons[index]
            button.setImageResource( if(card.isOpen) imagesId[index] else R.drawable.cardbackside )
        }
    }



    private fun isAllMatch():Boolean {

        cards.forEachIndexed{index, card ->
            if(!card.isMatch){ return false }
        }

        return true
    }

    private fun restartGame(){
        isGameStarted = true
        shuffleCard()
        for(button in imageButtons){
            button.setImageResource(R.drawable.cardbackside)
            button.setClickable(true)
        }
        binding.simpleChronometer.setBase(SystemClock.elapsedRealtime())
        binding.simpleChronometer.start()
        binding.simpleChronometer.setFormat("Time Running - %s")
    }

    private fun shuffleCard(){
        imagesId= imagesId.shuffled() as MutableList<Int>
        // cards -> add DATA
        cards = imageButtons.indices.map { index ->
            CardView(imagesId[index])
        }

    }




}