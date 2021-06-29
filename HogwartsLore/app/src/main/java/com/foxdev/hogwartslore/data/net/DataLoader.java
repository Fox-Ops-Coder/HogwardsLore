package com.foxdev.hogwartslore.data.net;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.foxdev.hogwartslore.objects.Person;

import java.util.function.Function;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class DataLoader {

    private static final String baseUrl = "https://hp-api.herokuapp.com/api/characters/";
    private final APIService apiService;

    @Inject
    public DataLoader() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .addInterceptor(interceptor)
                .build();

        apiService = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService.class);
    }

    /**
     * Загружает данные о персонажах из интернета
     * @param house Имя факультета
     * @param callBack Обработчик загруженных данных
     */
    public void getPersonsFromHome(@NonNull String house, Function<Person[], Void> callBack) {
        apiService.getPersonsFromHome(house).enqueue(new Callback<Person[]>() {
            @Override
            public void onResponse(Call<Person[]> call, Response<Person[]> response) {
                if (response.body() != null) {
                    callBack.apply(response.body());
                }
            }

            @Override
            public void onFailure(Call<Person[]> call, Throwable t) {
                callBack.apply(null);
            }
        });
    }
}
