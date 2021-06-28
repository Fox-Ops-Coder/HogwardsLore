package com.foxdev.hogwartslore.util.converters;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

public final class FloatConverter {

    @TypeConverter
    @Nullable
    public String fromFloat(@Nullable Float value) {
        return value != null ? String.valueOf(value) : null;
    }

    @TypeConverter
    @Nullable
    public Float toFloat(@Nullable String value) {
        return value != null ? Float.parseFloat(value) : null;
    }
}