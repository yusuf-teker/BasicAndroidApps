package com.yusufteker.picturememorygame

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class GameOverFragmentArgs(
  public val score: Int
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("score", this.score)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): GameOverFragmentArgs {
      bundle.setClassLoader(GameOverFragmentArgs::class.java.classLoader)
      val __score : Int
      if (bundle.containsKey("score")) {
        __score = bundle.getInt("score")
      } else {
        throw IllegalArgumentException("Required argument \"score\" is missing and does not have an android:defaultValue")
      }
      return GameOverFragmentArgs(__score)
    }
  }
}
