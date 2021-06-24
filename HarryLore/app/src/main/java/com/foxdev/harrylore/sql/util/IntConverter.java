package com.foxdev.harrylore.sql.util;

import androidx.room.TypeConverter;

/**
 * Конвертер int<->String
 */
public final class IntConverter
{
    @TypeConverter
    public String fromInt(Integer value)
    {
        return value == null ? null : String.valueOf(value);
    }

    @TypeConverter
    public Integer toFloat(String value)
    {
        return value == null || value.isEmpty() ? null : Integer.valueOf(value);
    }
}