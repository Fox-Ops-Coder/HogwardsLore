package com.foxdev.harrylore.sql.util;

import androidx.room.TypeConverter;

/**
 * Конвертер bool<->int
 */
public final class BooleanConverter
{
    @TypeConverter
    public int fromBoolean(boolean value) { return value ? 1 : 0; }

    @TypeConverter
    public boolean toBoolean(int value) { return value != 0; }
}
