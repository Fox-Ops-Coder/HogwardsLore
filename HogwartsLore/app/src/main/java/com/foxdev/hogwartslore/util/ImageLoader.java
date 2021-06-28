package com.foxdev.hogwartslore.util;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

public final class ImageLoader {

    public static void loadImage(@NonNull String url,
                                 @NonNull ImageView placeholder) {
        Picasso.get().load(url)
                .into(placeholder);
    }
}
