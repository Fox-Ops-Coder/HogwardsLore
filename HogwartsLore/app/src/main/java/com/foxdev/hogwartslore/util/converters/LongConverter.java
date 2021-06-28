package com.foxdev.hogwartslore.util.converters;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import java.sql.Date;

public final class LongConverter {

    @TypeConverter
    @Nullable
    public String fromLong(@Nullable Long value) {
        return value == null ? null : String.valueOf(Date.valueOf(value.toString()));
    }

    @TypeConverter
    @Nullable
    public Long toLong(@Nullable String value) {
        return value == null ? null : Date.valueOf(value).getTime();
    }
}