package com.foxdev.hogwartslore.ui.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.foxdev.hogwartslore.R;
import com.foxdev.hogwartslore.objects.Person;
import com.foxdev.hogwartslore.objects.Wand;
import com.foxdev.hogwartslore.ui.fragments.BlackHole;
import com.foxdev.hogwartslore.ui.fragments.HomeFragment;
import com.foxdev.hogwartslore.ui.fragments.PersonFragment;
import com.foxdev.hogwartslore.ui.fragments.ProgressFragment;
import com.foxdev.hogwartslore.ui.fragments.WandFragment;
import com.foxdev.hogwartslore.util.viewmodels.PersonViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public final class CoreActivity extends AppCompatActivity {

    private PersonViewModel personViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new HomeFragment())
                .addToBackStack("Home")
                .commit();

        personViewModel = new ViewModelProvider(this)
                .get(PersonViewModel.class);

        personViewModel.getPersons().observe(this, people -> {
            if (people != null) {
                showPersons(people);
            } else {
                showMessage(String.format(
                        "%s%n%s",
                        "Для данного факультета данных нет",
                        "Попробуйте подключиться к сети и загрузить их"));
            }
        });
    }

    public void loadPersons(@NonNull String house) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new ProgressFragment())
                .addToBackStack("loading")
                .commit();

        personViewModel.getPersonsFromHome(house);
    }

    private void showPersons(@NonNull Person[] persons) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, PersonFragment.newInstance(persons))
                .addToBackStack("PersonList")
                .commit();
    }

    public void showWand(Wand wand) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, WandFragment.newInstance(wand))
                .addToBackStack("Wand")
                .commit();
    }

    public void showMessage(@NonNull String message) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, BlackHole.newInstance(message))
                .addToBackStack("BlackHole")
                .commit();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (fragment instanceof HomeFragment) finish();
        else if (fragment instanceof PersonFragment) {
            getSupportFragmentManager().popBackStack();
            super.onBackPressed();
        }
        else if (fragment instanceof ProgressFragment){
        }
        else super.onBackPressed();
    }
}