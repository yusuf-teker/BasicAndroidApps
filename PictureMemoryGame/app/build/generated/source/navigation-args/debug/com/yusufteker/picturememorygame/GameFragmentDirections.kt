package com.yusufteker.picturememorygame

import android.os.Bundle
import androidx.navigation.NavDirections
import kotlin.Int

public class GameFragmentDirections private constructor() {
  private data class ActionGameFragmentToGameWonFragment(
    public val score: Int
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.action_gameFragment_to_gameWonFragment

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putInt("score", this.score)
      return result
    }
  }

  private data class ActionGameFragmentToGameOverFragment(
    public val score: Int
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.action_gameFragment_to_gameOverFragment

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putInt("score", this.score)
      return result
    }
  }

  public companion object {
    public fun actionGameFragmentToGameWonFragment(score: Int): NavDirections =
        ActionGameFragmentToGameWonFragment(score)

    public fun actionGameFragmentToGameOverFragment(score: Int): NavDirections =
        ActionGameFragmentToGameOverFragment(score)
  }
}
