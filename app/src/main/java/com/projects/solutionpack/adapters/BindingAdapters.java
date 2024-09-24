package com.projects.solutionpack.adapters;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("app:imageResource")
    public static void setImageResource(ImageView imageView, int resourceId) {
        if (resourceId != 0) {
            imageView.setImageResource(resourceId);
        }
    }
}
