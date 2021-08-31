package com.yusufteker.picturememorygame

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class GameOverFragmentDirections private constructor() {
  public companion object {
    public fun actionGameOverFragmentToGameFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_gameOverFragment_to_gameFragment)
  }
}
