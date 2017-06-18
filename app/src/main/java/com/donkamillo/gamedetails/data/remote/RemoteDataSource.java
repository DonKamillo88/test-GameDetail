package com.donkamillo.gamedetails.data.remote;

import android.content.Context;

import com.donkamillo.gamedetails.data.DataSource;
import com.donkamillo.gamedetails.data.local.SharedPreferencesManager;
import com.donkamillo.gamedetails.data.models.GameData;
import com.donkamillo.gamedetails.data.models.PlayerInfo;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


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
            public void onResponse(Call<GameData> call, Response<GameData> response) {
                long today = new Date().getTime();
                SharedPreferencesManager.saveCacheDate(today, context);
                SharedPreferencesManager.saveCache(response.body(), context);

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GameData> call, Throwable t) {
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
            public void onResponse(Call<PlayerInfo> call, Response<PlayerInfo> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PlayerInfo> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
