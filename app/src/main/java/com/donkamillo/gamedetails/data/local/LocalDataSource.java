package com.donkamillo.gamedetails.data.local;

import android.content.Context;

import com.donkamillo.gamedetails.data.DataSource;

import javax.inject.Inject;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class LocalDataSource extends DataSource {


    private SharedPreferencesManager preferencesManager;

    @Inject
    public LocalDataSource(SharedPreferencesManager preferencesManager) {
        this.preferencesManager = preferencesManager;
    }

    @Override
    public void getGames(final Context context, GetGamesCallback callback) {
        callback.onSuccess(preferencesManager.loadCache());
    }

    @Override
    public void getPlayerInfo(final Context context, GetPlayerInfoCallback callback) {
        // nope
    }

    @Override
    public void unSubscribe() {
        // nope
    }
}
