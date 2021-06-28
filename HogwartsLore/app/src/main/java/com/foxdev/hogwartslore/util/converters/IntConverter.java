package com.foxdev.hogwartslore.util.converters;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

public final class IntConverter {

    @TypeConverter
    @Nullable
    public String fromInt(@Nullable Integer value) {
        return value == null ? null : String.valueOf(value);
    }

    @TypeConverter
    @Nullable
    public Integer toInt(@Nullable String value) {
        return value == null || value.isEmpty() ? null : Integer.parseInt(value);
    }
}