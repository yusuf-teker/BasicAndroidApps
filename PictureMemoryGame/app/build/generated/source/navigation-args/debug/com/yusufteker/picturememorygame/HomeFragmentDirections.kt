package com.yusufteker.picturememorygame

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class HomeFragmentDirections private constructor() {
  public companion object {
    public fun actionHomeFragmentToGameFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_homeFragment_to_gameFragment)
  }
}
