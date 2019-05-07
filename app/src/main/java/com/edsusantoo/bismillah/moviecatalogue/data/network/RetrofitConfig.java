package com.edsusantoo.bismillah.moviecatalogue.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private static Retrofit getRetrofit() {

        Gson gsonBuilder = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .build();
    }

    public static ApiServices getApiServices() {

        return getRetrofit().create(ApiServices.class);
    }


}
