package com.foxdev.harrylore.sql;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.foxdev.harrylore.entity.Person;
import com.foxdev.harrylore.entity.Wand;

import java.util.List;

@Dao
public abstract class PersonDao
{
    @Insert
    protected abstract long insertPerson(Person person);

    @Insert
    protected abstract void insertWand(Wand wand);

    @Query("SELECT * FROM Wands WHERE wandId = :wandId")
    protected abstract Wand getWandById(final long wandId);

    @Query("SELECT * FROM Persons WHERE house = :house")
    protected abstract List<Person> getPersonsByHouse(String house);

    //TODO Убрать отсюда все поля кроме Id
    @Query("SELECT * FROM persons WHERE name = :name")
    protected abstract Person getPersonByName(String name);

    public List<Person> getPersonsFromHouse(String house)
    {
        List<Person> personList = getPersonsByHouse(house);

        if (personList != null)
        {
            for (Person person : personList) person.setWand(getWandById(person.getPersonId()));
        }

        return personList;
    }

    @Query("SELECT * FROM persons WHERE house = :house LIMIT 1")
    protected abstract Person any(String house);

    /**
     * Обновляет данные по персонажам из факультета
     * @param personList Список персонажей
     * @param house Имя факультета
     */
    @Transaction
    public void updateLocalData(List<Person> personList, String house)
    {
        if (any(house) != null)
        {
            for (Person person : personList)
            {
                Person existing = getPersonByName(person.getName());

                if (existing == null)
                {
                    final int newPersonId = (int) insertPerson(person);

                    if (person.getWand() != null)
                    {
                        Wand newWand = person.getWand();
                        newWand.setWandId(newPersonId);
                        insertWand(newWand);
                    }
                }
            }
        }
        else
        {
            for (Person person : personList)
            {
                final int newPersonId = (int) insertPerson(person);
                Wand personWand = person.getWand();
                if (!personWand.getWood().isEmpty())
                {
                    personWand.setWandId(newPersonId);
                    insertWand(personWand);
                }
            }
        }
    }
}