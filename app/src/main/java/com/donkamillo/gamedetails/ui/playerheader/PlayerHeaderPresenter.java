package com.donkamillo.gamedetails.ui.playerheader;

import android.content.Context;

import com.donkamillo.gamedetails.R;
import com.donkamillo.gamedetails.data.DataRepository;
import com.donkamillo.gamedetails.data.DataSource;
import com.donkamillo.gamedetails.data.models.PlayerInfo;

import javax.inject.Inject;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class PlayerHeaderPresenter implements PlayerHeaderContract.Presenter {

    private DataRepository dataRepository;
    private PlayerHeaderContract.View view;
    private DataSource dataSource;
    private Context context;

  //  @Inject
    public PlayerHeaderPresenter(Context context, DataRepository dataRepository) {
        this.dataRepository = dataRepository;
        this.context = context;
    }

    @Override
    public void getPlayerInfo() {
        if (view == null) {
            return;
        }

        dataSource = dataRepository.getPlayerInfoSource();

        dataSource.getPlayerInfo(context, new DataSource.GetPlayerInfoCallback() {
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

    @Override
    public void unSubscribe() {
        dataSource.unSubscribe();
    }

    @Override
    public void setView(Object view) {
        this.view = (PlayerHeaderContract.View) view;
    }
}
