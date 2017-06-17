package com.donkamillo.gamedetails.ui.games;

import android.content.Context;

import com.donkamillo.gamedetails.data.models.GameData;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public interface GamesContract {

    interface View {

        void updateGamesData(GameData gamesData);

        void setProgressBar(boolean b);

        void showToastMessage(String message);
    }

    interface Presenter {

        void getGames(Context context);

    }

}
