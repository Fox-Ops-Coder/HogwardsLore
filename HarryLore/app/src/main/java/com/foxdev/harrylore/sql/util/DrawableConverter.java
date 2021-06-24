package com.foxdev.harrylore.sql.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

/**
 * Конвертер Drawable<->byte[]
 */
public final class DrawableConverter
{
    @TypeConverter
    public byte[] DrawableToByte(Drawable value)
    {
        Bitmap bitmap = ((BitmapDrawable)value).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    @TypeConverter
    public Drawable ByteToDrawable(byte[] source)
    {
        return new BitmapDrawable(Resources.getSystem(),
                BitmapFactory.decodeByteArray(source, 0, source.length));
    }
}
