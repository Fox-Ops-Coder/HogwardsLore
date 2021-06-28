package com.foxdev.hogwartslore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageLoader {

    public static void loadImage(@NonNull String url,
                                 @NonNull ImageView placeholder) {
        Picasso.get().load(url)
                .into(placeholder);
    }

    public static void loadImage(@NonNull String url,
                                 @NonNull Target target) {
        Picasso.get().load(url)
                .into(target);
    }

    private static Target getTarget(@NonNull String url) {
        return new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(() -> {
                    File file = new File(Environment.getExternalStorageDirectory().getPath()
                            + "/" + url);

                    try {
                        final boolean isCreated = file.createNewFile();

                        if (isCreated) {
                            FileOutputStream outputStream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                            outputStream.flush();
                            outputStream.close();
                        } else {
                            throw new IOException("Файл не был создан");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
    }
}
