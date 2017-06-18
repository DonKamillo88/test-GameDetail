package com.donkamillo.gamedetails.data;

import android.content.Context;

import com.donkamillo.gamedetails.data.local.SharedPreferencesManager;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class DataRepository {
    private DataSource remoteDataSource;
    private DataSource localDataSource;

    private static DataRepository dataRepository;

    public DataRepository(DataSource remoteDataSource, DataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public static synchronized DataRepository getInstance(DataSource remoteDataSource, DataSource localDataSource) {
        if (dataRepository == null) {
            dataRepository = new DataRepository(remoteDataSource, localDataSource);
        }
        return dataRepository;
    }

    public void getGames(Context context, final DataSource.GetGamesCallback callback) {
        getDataSource(context).getGames(context, callback);
    }

    public DataSource getDataSource(Context context) {
        long today = new Date().getTime();
        long downloadDataDate = SharedPreferencesManager.loadCacheDate(context);
        if (downloadDataDate == 0 || today - downloadDataDate > TimeUnit.HOURS.toMillis(1)) {
            return remoteDataSource;
        } else {
            return localDataSource;
        }
    }

    public void getPlayerInfo(Context context, final DataSource.GetPlayerInfoCallback callback) {
        remoteDataSource.getPlayerInfo(context, callback);
    }

}
