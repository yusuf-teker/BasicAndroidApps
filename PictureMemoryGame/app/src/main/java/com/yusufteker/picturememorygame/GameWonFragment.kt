package com.yusufteker.picturememorygame

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.yusufteker.picturememorygame.databinding.FragmentGameWonBinding



class GameWonFragment : Fragment() {

    private var fragmentGameWonBinding: FragmentGameWonBinding? = null
    private val binding get() = fragmentGameWonBinding!!
    private lateinit var sharedPreference : SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentGameWonBinding = FragmentGameWonBinding.inflate(inflater, container, false)
        val score: Int

        val args= arguments?.let { GameWonFragmentArgs.fromBundle(it)}
        score = args!!.score



        binding.playAgainButton.setOnClickListener{
            Navigation.findNavController(it).navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }

        binding.scoreText.text = getString(R.string.show_score_won,score)

        sharedPreference = requireActivity().getSharedPreferences("com.yusufteker.picturememorygame", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        val highScore = sharedPreference.getInt("highScore",0)

        when {
            highScore == 0 -> {
                editor.putInt("highScore", score)
                binding.textView.text = getString(R.string.congratulations,score)
            }
            highScore<score -> {
                editor.putInt("highScore", score)
                binding.textView.text = getString(R.string.congratulations,score)
            }
            score == 100 -> {
                binding.textView.text = getString(R.string.congratulations2)
            }
            else -> {
                binding.textView.setVisibility(View.GONE)
            }
        }

        when {
            score>90 -> {
                binding.imageView.setImageResource(R.drawable.won_the_game)
            }
            score>80 -> {
                binding.imageView.setImageResource(R.drawable.won_the_game2)
            }
            else -> {
                binding.imageView.setImageResource(R.drawable.won_the_game3)
            }
        }

        editor.apply()
        editor.commit()











        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true) // show menu
        super.onCreate(savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        setHasOptionsMenu(true)
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}