package com.foxdev.harrylore.sql.util;

import androidx.room.TypeConverter;

/**
 * Конвертер float<->String
 */
public final class FloatConverter
{
    @TypeConverter
    public String fromFloat(Float value)
    {
        return value == null ? null : String.valueOf(value);
    }

    @TypeConverter
    public Float toFloat(String value)
    {
        return value == null || value.isEmpty() ? null : Float.valueOf(value);
    }
}