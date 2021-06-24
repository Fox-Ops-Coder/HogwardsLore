package com.foxdev.harrylore.sql;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.foxdev.harrylore.entity.Person;
import com.foxdev.harrylore.entity.Wand;

@Database(entities = { Person.class, Wand.class }, version = 1)
public abstract class HogwartsDb extends RoomDatabase
{
    public abstract PersonDao getPersonDao();
}