package com.donkamillo.gamedetails.di;

import com.donkamillo.gamedetails.ui.MainActivity;
import com.donkamillo.gamedetails.ui.games.GameListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DonKamillo on 28.06.2017.
 */

@Singleton
@Component(modules = {MainModule.class})
public interface GraphComponent {
    void inject(MainActivity mainActivity);

    void inject(GameListFragment gameListFragment);


}
