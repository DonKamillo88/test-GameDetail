package com.donkamillo.gamedetails.data;

import android.content.Context;

import com.donkamillo.gamedetails.data.models.GameData;
import com.donkamillo.gamedetails.data.models.PlayerInfo;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public abstract class DataSource {

    public interface GetGamesCallback {
        void onSuccess(GameData game);

        void onFailure(Throwable throwable);

    }

    public interface GetPlayerInfoCallback {

        void onSuccess(PlayerInfo playerInfo);

        void onFailure(Throwable throwable);

    }

    public abstract void getGames(Context context, GetGamesCallback callback);

    public abstract void getPlayerInfo(Context context, GetPlayerInfoCallback callback);
}
