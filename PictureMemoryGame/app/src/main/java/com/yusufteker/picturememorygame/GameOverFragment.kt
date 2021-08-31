package com.yusufteker.picturememorygame

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.yusufteker.picturememorygame.databinding.FragmentGameOverBinding


class GameOverFragment : Fragment() {

    private var fragmentGameOverBinding: FragmentGameOverBinding? = null
    private val binding get() = fragmentGameOverBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentGameOverBinding = FragmentGameOverBinding.inflate(inflater, container, false)
        val score: Int

        val args= arguments?.let { GameOverFragmentArgs.fromBundle(it)}
        score = args!!.score

        binding.scoreTextGo.text = getString(R.string.show_score_lose,score)

        binding.playAgainButton.setOnClickListener{
            Navigation.findNavController(it).navigate(GameOverFragmentDirections.actionGameOverFragmentToGameFragment())
        }

        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    /**Create Menu*/
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)

    }


}