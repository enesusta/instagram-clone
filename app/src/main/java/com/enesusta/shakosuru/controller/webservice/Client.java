package com.enesusta.shakosuru.controller.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private RestAPI mRestApi;

    public Client() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRestApi = retrofit.create(RestAPI.class);
    }


    public RestAPI getRestAPI() {
        return mRestApi;
    }


}
