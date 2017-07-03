package com.donkamillo.gamedetails.di.module;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.donkamillo.gamedetails.data.DataRepository;
import com.donkamillo.gamedetails.data.local.LocalDataSource;
import com.donkamillo.gamedetails.data.local.SharedPreferencesManager;
import com.donkamillo.gamedetails.data.remote.DropBox;
import com.donkamillo.gamedetails.data.remote.RemoteDataSource;
import com.donkamillo.gamedetails.di.App;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DonKamillo on 02.07.2017.
 */

@Module
public class DataModule {

    App app;

    public DataModule(App application) {
        app = application;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }

    @Provides
    @Singleton
    SharedPreferencesManager provideSharedPreferencesManager(SharedPreferences sharedPreferences, Gson gson) {
        return new SharedPreferencesManager(sharedPreferences, gson);
    }

    @Provides
    @Singleton
    DataRepository provideDataRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, SharedPreferencesManager preferencesManager) {
        return new DataRepository(remoteDataSource, localDataSource, preferencesManager);
    }

    @Provides
    @Singleton
    LocalDataSource provideLocalDataSource(SharedPreferencesManager preferencesManager) {
        return new LocalDataSource(preferencesManager);
    }

    @Provides
    @Singleton
    RemoteDataSource provideRemoteDataSource(SharedPreferencesManager preferencesManager, DropBox dropBox) {
        return new RemoteDataSource(preferencesManager, dropBox);
    }


}
