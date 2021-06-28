package com.foxdev.hogwartslore.data.sql;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.foxdev.hogwartslore.objects.Person;
import com.foxdev.hogwartslore.objects.Wand;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class PersonDao {

    @Insert(onConflict = REPLACE)
    protected abstract long insertPerson(Person person);

    @Insert(onConflict = REPLACE)
    protected abstract void insertWand(Wand wand);

    @Query("SELECT * FROM persons WHERE house = :house LIMIT 1")
    protected abstract Person any(String house);

    @Transaction
    public void insertPersons(Person[] persons, String house) {
        if (any(house) == null) {
            for (Person person : persons) {
                final long personId = insertPerson(person);
                Wand wand = person.wand;

                if (wand != null && !wand.isEmpty()) {
                    wand.wandId = personId;
                    insertWand(wand);
                }
            }
        }
    }

    @Query("SELECT * FROM Persons WHERE house = :house")
    protected abstract Person[] getPersons(String house);

    @Query("SELECT * FROM Wands WHERE wandId = :wandId")
    protected abstract Wand getPersonWand(long wandId);

    @Transaction
    public Person[] getPersonsFromHouse(String house) {
        Person[] persons = getPersons(house);
        for (Person person : persons) {
            person.wand = getPersonWand(person.personId);
        }

        return persons;
    }
}
