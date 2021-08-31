package com.yusufteker.picturememorygame

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.yusufteker.picturememorygame.databinding.FragmentGameBinding
import com.yusufteker.picturememorygame.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var fragmentHomeBinding: FragmentHomeBinding? = null
    private val binding get() = fragmentHomeBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.playButton.setOnClickListener{view: View ->
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToGameFragment())
        }

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true) // show menu
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