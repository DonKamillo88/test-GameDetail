package com.donkamillo.gamedetails.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.donkamillo.gamedetails.data.DataRepository;
import com.donkamillo.gamedetails.data.local.LocalDataSource;
import com.donkamillo.gamedetails.data.local.SharedPreferencesManager;
import com.donkamillo.gamedetails.data.remote.DropBoxService;
import com.donkamillo.gamedetails.data.remote.RemoteDataSource;
import com.donkamillo.gamedetails.ui.games.GamesPresenter;
import com.donkamillo.gamedetails.ui.playerheader.PlayerHeaderPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DonKamillo on 28.06.2017.
 */


@Module
public class MainModule {
    App app;

    public MainModule(App application) {
        app = application;
    }

    @Provides
    @Singleton
    protected Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    DropBoxService provideDropBoxService() {
        return new DropBoxService();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }

    @Provides
    @Singleton
    SharedPreferencesManager provideSharedPreferencesManager(SharedPreferences sharedPreferences) {
        return new SharedPreferencesManager(sharedPreferences);
    }

    @Provides
    @Singleton
    GamesPresenter provideGamesPresenter(DataRepository dataRepository) {
        return new GamesPresenter(app, dataRepository);
    }

    @Provides
    @Singleton
    PlayerHeaderPresenter providePlayerHeaderPresenter(DataRepository dataRepository) {
        return new PlayerHeaderPresenter(app, dataRepository);
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
    RemoteDataSource provideRemoteDataSource(SharedPreferencesManager preferencesManager, DropBoxService dropBoxService) {
        return new RemoteDataSource(preferencesManager, dropBoxService);
    }

}
