package com.donkamillo.gamedetails.ui.playerheader;

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

    interface Presenter<T> {

        void getPlayerInfo();

        void showLastLogin(boolean isShow);

        void unSubscribe();

        void setView(T view);
    }
}
