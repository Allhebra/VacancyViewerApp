package com.bereg.vacancyviewerapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 1 on 07.01.2018.
 */

public class RetrofitService {

    public static NgsApi getApi() {

        final Gson gson = new GsonBuilder()
                //.setLenient()
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rabota.ngs.ru/api/v1/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create(gson)) //Конвертер, необходимый для преобразования JSON'а в объекты
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(NgsApi.class);
    }
}
