package com.foxdev.hogwartslore.util.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.foxdev.hogwartslore.objects.Person;
import com.foxdev.hogwartslore.repository.PersonRepository;
import com.foxdev.hogwartslore.ui.fragments.PersonFragment;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public final class PersonViewModel extends ViewModel {
    private final PersonRepository repository;

    @Inject
    public PersonViewModel(@NonNull PersonRepository repository) {
        this.repository = repository;
    }

    //TODO Оно не должно быть сдесь
    public void getPersonsFromHome(@NonNull String house) {
        repository.getPersonsFromHome(house);
    }

    public LiveData<Person[]> getPersons() { return repository.getPersons(); }
}
