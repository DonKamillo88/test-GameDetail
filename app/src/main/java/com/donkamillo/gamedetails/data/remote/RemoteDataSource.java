package com.donkamillo.gamedetails.data.remote;

import android.content.Context;

import com.donkamillo.gamedetails.data.DataSource;
import com.donkamillo.gamedetails.data.local.SharedPreferencesManager;
import com.donkamillo.gamedetails.data.models.GameData;
import com.donkamillo.gamedetails.data.models.PlayerInfo;

import java.util.Date;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class RemoteDataSource extends DataSource {

    private static RemoteDataSource remoteDataSource;

    public static synchronized RemoteDataSource getInstance() {
        if (remoteDataSource == null) {
            remoteDataSource = new RemoteDataSource();
        }
        return remoteDataSource;
    }

    @Override
    public void getGames(final Context context, final GetGamesCallback callback) {
        Call<GameData> call;
        DropBox dropBox = DropBoxService.getService();

        call = dropBox.getGameData();
        call.enqueue(new Callback<GameData>() {
            @Override
            public void onResponse(Response<GameData> response, Retrofit retrofit) {
                long today = new Date().getTime();
                SharedPreferencesManager.saveCacheDate(today, context);
                SharedPreferencesManager.saveCache(response.body(), context);

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getPlayerInfo(final Context context, final GetPlayerInfoCallback callback) {
        Call<PlayerInfo> call;
        DropBox dropBox = DropBoxService.getService();

        call = dropBox.getPlayerInfo();
        call.enqueue(new Callback<PlayerInfo>() {
            @Override
            public void onResponse(Response<PlayerInfo> response, Retrofit retrofit) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
