package com.donkamillo.gamedetails.ui.playerheader;

import android.content.Context;

import com.donkamillo.gamedetails.data.models.PlayerInfo;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public interface PlayerHeaderContract {

    interface View {

        void showLastLogin(boolean isShow);

        void updatePlayerInfo(PlayerInfo playerInfo);

        void showErrorMessage(String string);
    }

    interface Presenter {

        void getPlayerInfo(Context context);

        void showLastLogin(boolean isShow);

        void unSubscribe();
    }
}
