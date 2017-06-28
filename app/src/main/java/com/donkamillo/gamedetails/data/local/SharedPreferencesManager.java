package com.donkamillo.gamedetails.data.local;

import android.content.SharedPreferences;

import com.donkamillo.gamedetails.data.models.GameData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class SharedPreferencesManager {

    private static final String CACHE = "cache";
    private static final String CACHE_DATE = "cache_date";

    private SharedPreferences sharedPrefs;

    @Inject
    public SharedPreferencesManager(SharedPreferences sharedPreferences) {
        this.sharedPrefs = sharedPreferences;
    }

    public void saveCache(GameData gameData) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(gameData);

        editor.putString(CACHE, json);
        editor.apply();
    }

    public GameData loadCache() {
        Gson gson = new Gson();
        String json = sharedPrefs.getString(CACHE, null);
        Type type = new TypeToken<GameData>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public void saveCacheDate(long date) {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putLong(CACHE_DATE, date);
        editor.apply();
    }

    public Long loadCacheDate() {
        return sharedPrefs.getLong(CACHE_DATE, 0);
    }
}
