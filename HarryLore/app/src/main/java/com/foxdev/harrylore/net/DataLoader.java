package com.foxdev.harrylore.net;

import android.os.Handler;
import android.os.Message;

import com.foxdev.harrylore.entity.Person;
import com.foxdev.harrylore.sql.util.DrawableConverter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;
import retrofit2.internal.EverythingIsNonNull;

/**
 * Класс служит для загрузки данных из интернета
 */
public final class DataLoader
{
    public static final byte NET_ERR = 1;
    public static final byte RESPONSE_DATA = 0;

    private static final class PersonCallback implements Callback<List<Person>>
    {
        private final Handler onLoad;
        private final Object sender;
        private final String house;

        public PersonCallback(Object sender, Handler onLoad, String house)
        {
            this.onLoad = onLoad;
            this.sender = sender;
            this.house = house;
        }

        /**
         * Метод зугружает изображения персонажей
         * @param personList
         */
        private void loadImages(List<Person> personList)
        {
            OkHttpClient httpClient = new OkHttpClient();
            Message newMessage = new Message();
            DrawableConverter converter = new DrawableConverter();

            for (Person person : personList)
            {
                String url = "https://" + person.getImage().substring(7);

                Request request = new Request.Builder()
                        .url(url)
                        .build();

                try (okhttp3.Response imageResponse = httpClient.newCall(request).execute())
                {
                    if (imageResponse.isSuccessful())
                    {
                        assert imageResponse.body() != null;
                        person.setImageBytes(converter.ByteToDrawable(imageResponse.body().bytes()));
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    sendErrMessage();
                }
            }

            newMessage.what = RESPONSE_DATA;
            newMessage.obj = new Object[]{ personList, sender, house };
            onLoad.sendMessage(newMessage);
        }

        /**
         * Метод отправляет сообщение, указывающее на ошибку во время загрузки данных
         */
        private void sendErrMessage()
        {
            Message newMessage = new Message();
            newMessage.what = NET_ERR;
            newMessage.obj = new Object[] { sender, house };
            onLoad.sendMessage(newMessage);
        }

        @Override
        @EverythingIsNonNull
        public void onResponse(Call<List<Person>> call, Response<List<Person>> response)
        {
            if (response.isSuccessful())
            {
                if (response.body() != null)
                {
                    ExecutorService loadImageAsync = Executors.newSingleThreadExecutor();
                    loadImageAsync.execute(() ->
                    {
                        loadImages(response.body());
                        loadImageAsync.shutdown();
                    });
                }
                else sendErrMessage();
            }
            else sendErrMessage();
        }

        @Override
        @EverythingIsNonNull
        public void onFailure(Call<List<Person>> call, Throwable t)
        {
            sendErrMessage();
        }
    }

    private final static String baseUrl = "https://hp-api.herokuapp.com/api/characters/";
    private final APIService apiService;

    public DataLoader()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);
    }

    /**
     * Метод загружает данные персонажей из указанного факультета
     * @param sender Объект, вызвавший метод
     * @param house Имя факультета
     * @param onLoad Обработчик сообщейний. Он используется для обработки результата выполнения
     */
    public void getPersonsFromHome(Object sender, String house, Handler onLoad)
    {
        apiService.getPersonsFromHome(house).enqueue(new PersonCallback(sender, onLoad, house));
    }
}