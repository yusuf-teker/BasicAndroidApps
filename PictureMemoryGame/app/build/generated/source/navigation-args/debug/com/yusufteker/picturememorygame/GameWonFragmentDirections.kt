package com.yusufteker.picturememorygame

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class GameWonFragmentDirections private constructor() {
  public companion object {
    public fun actionGameWonFragmentToGameFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_gameWonFragment_to_gameFragment)
  }
}
