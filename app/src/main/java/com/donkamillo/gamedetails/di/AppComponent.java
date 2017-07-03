package com.donkamillo.gamedetails.di;

import com.donkamillo.gamedetails.di.module.ApiModule;
import com.donkamillo.gamedetails.di.module.DataModule;
import com.donkamillo.gamedetails.di.module.MainModule;
import com.donkamillo.gamedetails.ui.MainActivity;
import com.donkamillo.gamedetails.ui.games.GameListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DonKamillo on 28.06.2017.
 */

@Singleton
@Component(modules = {MainModule.class, DataModule.class, ApiModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(GameListFragment gameListFragment);


}
