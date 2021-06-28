package com.foxdev.hogwartslore.util.converters;

import androidx.room.TypeConverter;

public final class BooleanConverter {

    @TypeConverter
    public boolean fromInt(int value) {
        return value != 0;
    }

    @TypeConverter
    public int toInt(boolean value) {
        return value ? 1 : 0;
    }
}