package com.donkamillo.gamedetails.di.module;

import android.app.Application;

import com.donkamillo.gamedetails.data.DataRepository;
import com.donkamillo.gamedetails.di.App;
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
    GamesPresenter provideGamesPresenter(DataRepository dataRepository) {
        return new GamesPresenter(app, dataRepository);
    }

    @Provides
    @Singleton
    PlayerHeaderPresenter providePlayerHeaderPresenter(DataRepository dataRepository) {
        return new PlayerHeaderPresenter(app, dataRepository);
    }



}
