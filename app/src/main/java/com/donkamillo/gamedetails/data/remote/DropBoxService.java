package com.donkamillo.gamedetails.data.remote;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DonKamillo on 15.06.2017.
 */

public class DropBoxService {
    private static final String API_URL = "https://www.dl.dropboxusercontent.com";

    public static DropBox getService() {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DropBox.class);
    }
}