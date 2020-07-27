package com.example.expresso.sapthagiri.yogirproduct.NetworkServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static ServiceBuilder serviceBuilder;
    private static Retrofit retrofit;

    public ServiceBuilder() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.4:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ServiceBuilder getInstance() {
        if(serviceBuilder == null) {
            serviceBuilder = new ServiceBuilder();
        }
        return serviceBuilder;
    }

    public RetrofitServiceApi getApi() {
        return retrofit.create(RetrofitServiceApi.class);
    }
}
