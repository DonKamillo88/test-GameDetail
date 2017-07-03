package com.donkamillo.gamedetails.data;

import com.donkamillo.gamedetails.data.local.SharedPreferencesManager;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class DataRepository {

    private DataSource remoteDataSource;
    private DataSource localDataSource;
    private SharedPreferencesManager preferencesManager;

    public DataRepository(DataSource remoteDataSource, DataSource localDataSource, SharedPreferencesManager preferencesManager) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.preferencesManager = preferencesManager;
    }

    public DataSource getGameDataSource() {
        long today = new Date().getTime();
        long downloadDataDate = preferencesManager.loadCacheDate();
        if (downloadDataDate == 0 || today - downloadDataDate > TimeUnit.HOURS.toMillis(1)) {
            return remoteDataSource;
        } else {
            return localDataSource;
        }
    }

    public DataSource getPlayerInfoSource() {
        return remoteDataSource;
    }


}
