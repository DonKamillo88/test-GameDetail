package com.donkamillo.gamedetails.data.remote;

import android.content.Context;

import com.donkamillo.gamedetails.data.DataSource;
import com.donkamillo.gamedetails.data.local.SharedPreferencesManager;
import com.donkamillo.gamedetails.data.models.GameData;
import com.donkamillo.gamedetails.data.models.PlayerInfo;

import java.util.Date;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by DonKamillo on 16.06.2017.
 */

public class RemoteDataSource extends DataSource {

    private CompositeDisposable compositeDisposable;
    private SharedPreferencesManager preferencesManager;
    private DropBoxService dropBoxService;

    @Inject
    public RemoteDataSource(SharedPreferencesManager preferencesManager, DropBoxService dropBoxService) {
        this.preferencesManager = preferencesManager;
        this.dropBoxService = dropBoxService;
    }

    @Override
    public void getGames(final Context context, final GetGamesCallback callback) {
        DropBox dropBox = dropBoxService.getService();

        DisposableSingleObserver<GameData> disposableSingleObserver = new DisposableSingleObserver<GameData>() {
            @Override
            public void onSuccess(GameData gameData) {
                long today = new Date().getTime();
                preferencesManager.saveCacheDate(today);
                preferencesManager.saveCache(gameData);

                callback.onSuccess(gameData);
            }

            @Override
            public void onError(Throwable throwable) {
                callback.onFailure(throwable);
            }
        };

        if (!getCompositeDisposable().isDisposed()) {
            Single<GameData> newsModelSingle = dropBox.getGameData();
            Disposable gameDataDisposable = newsModelSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(disposableSingleObserver);
            compositeDisposable.add(gameDataDisposable);
        }
    }


    @Override
    public void getPlayerInfo(final Context context, final GetPlayerInfoCallback callback) {
        DropBox dropBox = dropBoxService.getService();

        DisposableSingleObserver<PlayerInfo> disposableSingleObserver = new DisposableSingleObserver<PlayerInfo>() {
            @Override
            public void onSuccess(PlayerInfo playerInfo) {
                callback.onSuccess(playerInfo);
            }

            @Override
            public void onError(Throwable throwable) {
                callback.onFailure(throwable);
            }
        };

        if (!getCompositeDisposable().isDisposed()) {
            Single<PlayerInfo> newsModelSingle = dropBox.getPlayerInfo();
            Disposable playerInfoDisposable = newsModelSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(disposableSingleObserver);
            compositeDisposable.add(playerInfoDisposable);
        }

    }

    @Override
    public void unSubscribe() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
    }

    private CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }
}
