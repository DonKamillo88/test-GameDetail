package com.donkamillo.gamedetails.data.local;

import android.content.Context;

import com.donkamillo.gamedetails.data.DataSource;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class LocalDataSource extends DataSource {

    private static LocalDataSource localDataSource;

    public static synchronized LocalDataSource getInstance() {
        if (localDataSource == null) {
            localDataSource = new LocalDataSource();
        }
        return localDataSource;
    }

    @Override
    public void getGames(final Context context, GetGamesCallback callback) {
        callback.onSuccess(SharedPreferencesManager.loadCache(context));
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
