package com.donkamillo.gamedetails.data.remote;

import com.donkamillo.gamedetails.data.models.GameData;
import com.donkamillo.gamedetails.data.models.PlayerInfo;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by DonKamillo on 15.06.2017.
 */

public interface DropBox {

    @GET("/s/2ewt6r22zo4qwgx/gameData.json")
    Call<GameData> getGameData();

    @GET("/s/5zz3hibrxpspoe5/playerInfo.json")
    Call<PlayerInfo> getPlayerInfo();

}
