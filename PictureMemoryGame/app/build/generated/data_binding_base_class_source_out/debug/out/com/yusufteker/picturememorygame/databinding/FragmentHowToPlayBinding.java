// Generated by view binder compiler. Do not edit!
package com.yusufteker.picturememorygame.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yusufteker.picturememorygame.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHowToPlayBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final CardView cardView;

  @NonNull
  public final ImageView howToPlayImage;

  @NonNull
  public final TextView textView1;

  @NonNull
  public final TextView textView3;

  private FragmentHowToPlayBinding(@NonNull FrameLayout rootView, @NonNull CardView cardView,
      @NonNull ImageView howToPlayImage, @NonNull TextView textView1, @NonNull TextView textView3) {
    this.rootView = rootView;
    this.cardView = cardView;
    this.howToPlayImage = howToPlayImage;
    this.textView1 = textView1;
    this.textView3 = textView3;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHowToPlayBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHowToPlayBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_how_to_play, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHowToPlayBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardView;
      CardView cardView = ViewBindings.findChildViewById(rootView, id);
      if (cardView == null) {
        break missingId;
      }

      id = R.id.howToPlayImage;
      ImageView howToPlayImage = ViewBindings.findChildViewById(rootView, id);
      if (howToPlayImage == null) {
        break missingId;
      }

      id = R.id.textView1;
      TextView textView1 = ViewBindings.findChildViewById(rootView, id);
      if (textView1 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      return new FragmentHowToPlayBinding((FrameLayout) rootView, cardView, howToPlayImage,
          textView1, textView3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
