package com.donkamillo.gamedetails.data.remote;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DonKamillo on 15.06.2017.
 */

public class DropBoxService {
    public static String API_URL = "https://www.dl.dropboxusercontent.com";

    public static DropBox getService() {

        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DropBox.class);
    }
}