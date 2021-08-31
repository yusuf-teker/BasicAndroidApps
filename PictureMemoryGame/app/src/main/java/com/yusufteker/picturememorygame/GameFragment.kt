package com.yusufteker.picturememorygame

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.findNavController
import com.yusufteker.picturememorygame.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var cards:List<CardView>
    private lateinit var  imageButtons: List<ImageButton>
    private lateinit var imagesId: MutableList<Int>
    private  var lastOpenCardIndex = -1
    private  var control = -1
    private  var isGameStarted = false
    private  var oldOpenCardIndex = listOf(-1,-1)
    private var fragmentGameBinding: FragmentGameBinding? = null
    private val binding get() = fragmentGameBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentGameBinding = FragmentGameBinding.inflate(inflater, container, false)

        /**Add Data*/
        imagesId= mutableListOf(R.drawable.carousel,R.drawable.coffe,R.drawable.crownanemone,
            R.drawable.octopus,R.drawable.owl,R.drawable.westernstripedalbatros,R.drawable.carousel,R.drawable.coffe,R.drawable.crownanemone,
            R.drawable.octopus,R.drawable.owl,R.drawable.westernstripedalbatros)
        imageButtons= listOf(binding.imageView1,binding.imageView2,binding.imageView3,binding.imageView4,binding.imageView5,
            binding.imageView6,binding.imageView7,binding.imageView8,binding.imageView9,binding.imageView10,
            binding.imageView11,binding.imageView12)

        shuffleCard()/**Shuffle CARDS*/
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
        restartGame() /**StartGame*/

        return binding.root
    }

    //update model when each clicked on the cards
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
                val score =  if(time > 50) 0 else if(time > 25) 100 - time*17/50   else 100 - time/2
                binding.simpleChronometer.setBase(SystemClock.elapsedRealtime())
                if(score > 70){
                    view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(score.toInt()))
                }else{
                    view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment(score.toInt()))
                }
            }

        }else{
            imageButtons[lastOpenCardIndex].setClickable(true)
            oldOpenCardIndex = listOf(position,lastOpenCardIndex)
            lastOpenCardIndex = -1
            control = 1
        }
    }

    //update view
    private fun updateViews() {
        //Update all views !
        cards.forEachIndexed{index, card ->
            val button = imageButtons[index]
            button.setImageResource( if(card.isOpen) imagesId[index] else R.drawable.cardbackside )
        }
    }

    private fun isAllMatch():Boolean {
        cards.forEachIndexed{ _, card ->
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