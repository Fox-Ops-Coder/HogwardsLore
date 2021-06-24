package com.foxdev.harrylore.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import com.foxdev.harrylore.R;
import com.foxdev.harrylore.entity.Person;
import com.foxdev.harrylore.entity.Wand;
import com.foxdev.harrylore.fragments.BlackHole;
import com.foxdev.harrylore.fragments.HomeFragment;
import com.foxdev.harrylore.fragments.PersonsFragment;
import com.foxdev.harrylore.fragments.WandFragment;
import com.foxdev.harrylore.sql.HogwartsDb;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class CoreActivity extends AppCompatActivity
{
    private HogwartsDb appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        appDatabase = Room.databaseBuilder(this, HogwartsDb.class, "HogwartsDb")
                .build();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragments_container, new HomeFragment())
                .addToBackStack("home")
                .commit();
    }

    /**
     * Обрабатывает ситуацию, когда загрузка данных успешно завершилась
     * @param personList Список персонажей
     * @param house Название факультета
     */
    public void renderPersons(List<Person> personList, String house)
    {
        //Добавляет или обновляет данные в базе данных
        ExecutorService uploadDb = Executors.newSingleThreadExecutor();
        uploadDb.execute(() ->
        {
            appDatabase.getPersonDao().updateLocalData(personList, house);
            uploadDb.shutdown();
        });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragments_container, PersonsFragment.newInstance(personList))
                .addToBackStack("PersonList")
                .commit();
    }

    /**
     * Обрабатывает ситуацию, когда во время загрузки данных произошла ошибка
     * @param house
     */
    public void renderPersonWhenNetErr(String house)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ExecutorService getFromDb = Executors.newSingleThreadExecutor();
        getFromDb.execute(() ->
        {
            //Загружает список персонажей, сохранённых в базе данных
            List<Person> personList = appDatabase.getPersonDao().getPersonsFromHouse(house);

            if (!personList.isEmpty())
            {
                runOnUiThread(() ->
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragments_container,
                                        PersonsFragment.newInstance(personList))
                                .addToBackStack("PersonList")
                                .commit());
            }
            else
            {
                runOnUiThread(() -> createBlackHole(String.format(
                        "%s%n%s",
                        "Для данного факультета данных нет",
                        "Попробуйте подключиться к сети и загрузить их")));
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final int backCount = fragmentManager.getBackStackEntryCount();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragments_container);

        if (backCount == 1 || fragment instanceof HomeFragment)
        {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            super.onBackPressed();
        }
        else fragmentManager.popBackStack();
    }

    /**
     * Создаёт и показывает фрагмент с указанным сообщением
     * @param message Сообщение
     */
    public void createBlackHole(String message)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragments_container, BlackHole.newInstance(message))
                .addToBackStack("BlackHole")
                .commit();
    }

    /**
     * Создаёт и показывает фрагмент с информацией о волшебной палочке
     * @param wand Волшебная палочка
     */
    public void showMagicWand(Wand wand)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragments_container, WandFragment.newInstance(wand))
                .addToBackStack("Wand")
                .commit();
    }
}