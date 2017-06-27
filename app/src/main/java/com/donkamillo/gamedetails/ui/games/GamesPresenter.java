package com.donkamillo.gamedetails.ui.games;

import android.content.Context;

import com.donkamillo.gamedetails.R;
import com.donkamillo.gamedetails.data.DataRepository;
import com.donkamillo.gamedetails.data.DataSource;
import com.donkamillo.gamedetails.data.local.LocalDataSource;
import com.donkamillo.gamedetails.data.models.GameData;
import com.donkamillo.gamedetails.data.remote.RemoteDataSource;


/**
 * Created by DonKamillo on 16.06.2017.
 */

public class GamesPresenter implements GamesContract.Presenter {

    private DataRepository dataRepository;

    private GamesContract.View view;

    private DataSource dataSource;

    public GamesPresenter(GamesContract.View view) {
        this.view = view;
        this.dataRepository = DataRepository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance());
    }


    @Override
    public void getGames(final Context context) {
        if (view == null) {
            return;
        }

        view.setProgressBar(true);

        dataSource = dataRepository.getGameDataSource(context);

        dataSource.getGames(context, new DataSource.GetGamesCallback() {
            @Override
            public void onSuccess(GameData games) {
                if (view != null) {
                    view.updateGamesData(games);
                    view.setProgressBar(false);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                if (view != null) {
                    view.setProgressBar(false);
                    view.showToastMessage(context.getString(R.string.error_msg));
                }
            }

        });
    }

    @Override
    public void unSubscribe() {
        dataSource.unSubscribe();
    }

}
