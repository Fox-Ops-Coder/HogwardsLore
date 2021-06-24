package com.foxdev.harrylore.net;

import com.foxdev.harrylore.entity.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService
{
    @GET("house/{home}")
    Call<List<Person>> getPersonsFromHome(@Path("home") String home);
}
