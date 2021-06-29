package com.foxdev.hogwartslore.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.foxdev.hogwartslore.data.net.DataLoader;
import com.foxdev.hogwartslore.data.sql.HogwartsDb;
import com.foxdev.hogwartslore.objects.Person;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public final class PersonRepository {
    private final DataLoader dataLoader;
    private final HogwartsDb hogwartsDb;

    private final MutableLiveData<Person[]> persons = new MutableLiveData<>();

    private String house;

    @Inject
    public PersonRepository(DataLoader dataLoader, HogwartsDb hogwartsDb) {
        this.dataLoader = dataLoader;
        this.hogwartsDb = hogwartsDb;
    }
    
    public void getPersonsFromHome(@NonNull String house) {
        this.house = house;
        dataLoader.getPersonsFromHome(house, this::processData);
    }

    /**
     * Метод обрабатывает данные полученные при попытке загрузки из интернета
     * @param people Массив персонажей
     * @return null
     */
    private Void processData(@Nullable Person[] people) {
        ExecutorService dbThread = Executors.newSingleThreadExecutor();

        if (people != null) {
            dbThread.execute(() -> hogwartsDb.getPersonDao().insertPersons(people, house));
            persons.postValue(people);
        } else {
            dbThread.execute(() -> {
                Person[] personsArray = hogwartsDb.getPersonDao()
                        .getPersonsFromHouse(house);

                if (personsArray.length == 0) persons.postValue(null);
                else persons.postValue(personsArray);
            });
        }

        dbThread.shutdown();

        return null;
    }

    @NonNull
    public LiveData<Person[]> getPersons() { return persons; }
}
