package com.donkamillo.gamedetails.ui.playerheader;

import android.content.Context;

import com.donkamillo.gamedetails.R;
import com.donkamillo.gamedetails.data.DataRepository;
import com.donkamillo.gamedetails.data.DataSource;
import com.donkamillo.gamedetails.data.local.LocalDataSource;
import com.donkamillo.gamedetails.data.models.PlayerInfo;
import com.donkamillo.gamedetails.data.remote.RemoteDataSource;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class PlayerHeaderPresenter implements PlayerHeaderContract.Presenter {

    private DataRepository dataRepository;
    private PlayerHeaderContract.View view;

    public PlayerHeaderPresenter(PlayerHeaderContract.View view) {
        this.view = view;
        this.dataRepository = DataRepository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance());
    }

    @Override
    public void getPlayerInfo(final Context context) {
        if (view == null) {
            return;
        }

        dataRepository.getPlayerInfo(context, new DataSource.GetPlayerInfoCallback() {
            @Override
            public void onSuccess(PlayerInfo playerInfo) {
                if (view != null) {
                    view.updatePlayerInfo(playerInfo);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                if (view != null) {
                    view.showErrorMessage(context.getString(R.string.error_msg));
                }
            }

        });

    }

    @Override
    public void showLastLogin(boolean isShow) {
        view.showLastLogin(isShow);
    }
}
