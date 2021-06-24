package com.foxdev.harrylore.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.foxdev.harrylore.R;
import com.foxdev.harrylore.activities.CoreActivity;
import com.foxdev.harrylore.entity.Person;
import com.foxdev.harrylore.entity.constants.Houses;
import com.foxdev.harrylore.net.DataLoader;

import java.util.List;

public final class HomeFragment extends Fragment
{
    private final static DataLoader personsLoader = new DataLoader();

    //Обработчик сообщений
    private final static Handler messageHandler = new Handler(Looper.getMainLooper())
    {
        /**
         * Метод обрабатывает случай успешного получения данных из интернета
         * @param msg Сообщение
         */
        private void handleResultSuccess(Message msg)
        {
            Object[] args = (Object[]) msg.obj;

            List<Person> personList = (List<Person>) args[0];
            Fragment fragment = (Fragment) args[1];
            String house = (String) args[2];

            ((CoreActivity)fragment.requireActivity())
                    .renderPersons(personList, house);
        }

        /**
         * Метод обрабатыве неуспешное получение данных из интернета
         * @param msg Сообщение
         */
        private void handleResultFailed(Message msg)
        {
            Object[] args = (Object[]) msg.obj;

            Fragment fragment = (Fragment) args[0];
            String house = (String) args[1];

            ((CoreActivity) fragment.requireActivity())
                    .renderPersonWhenNetErr(house);
        }

        /**
         * Обрабатывает приходящие сообщения
         * @param msg Сообщение
         */
        @Override
        public void handleMessage(@NonNull Message msg)
        {
            super.handleMessage(msg);

            switch (msg.what)
            {
                case DataLoader.RESPONSE_DATA:
                    handleResultSuccess(msg);
                    break;

                case DataLoader.NET_ERR:
                    handleResultFailed(msg);
                    break;
            }
        }
    };

    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setHouseListener(view.findViewById(R.id.gryffindor_button), Houses.Gryffindor);
        setHouseListener(view.findViewById(R.id.slitherin_button), Houses.Slytherin);
        setHouseListener(view.findViewById(R.id.hufflepuff_button), Houses.Hufflepuff);
        setHouseListener(view.findViewById(R.id.ravenclaw_button), Houses.Ravenclaw);

        return view;
    }

    /**
     * Связывает обработчик нажатия на кнопку и факультет
     * @param button Кнопка
     * @param houseName Имя факультета
     */
    private void setHouseListener(Button button, String houseName)
    {
        button.setOnClickListener(V -> personsLoader.getPersonsFromHome(this, houseName, messageHandler));
    }
}