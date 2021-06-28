package com.foxdev.hogwartslore.data.sql;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.foxdev.hogwartslore.objects.Person;
import com.foxdev.hogwartslore.objects.Wand;

@Database(entities = { Person.class, Wand.class }, version = 1)
public abstract class HogwartsDb extends RoomDatabase {
    public abstract PersonDao getPersonDao();
}
