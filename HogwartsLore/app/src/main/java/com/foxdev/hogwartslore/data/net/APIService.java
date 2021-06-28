package com.foxdev.hogwartslore.data.net;

import androidx.annotation.NonNull;

import com.foxdev.hogwartslore.objects.Person;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {
    @GET("house/{home}")
    @NonNull
    Call<Person[]> getPersonsFromHome(@NonNull @Path("home") String home);
}
