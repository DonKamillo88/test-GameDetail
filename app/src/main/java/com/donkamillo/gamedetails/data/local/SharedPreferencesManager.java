package com.donkamillo.gamedetails.data.local;

import android.content.SharedPreferences;

import com.donkamillo.gamedetails.data.models.GameData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class SharedPreferencesManager {

    private static final String CACHE = "cache";
    private static final String CACHE_DATE = "cache_date";

    private SharedPreferences sharedPrefs;
    private Gson gson;

    public SharedPreferencesManager(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPrefs = sharedPreferences;
        this.gson = gson;
    }

    public void saveCache(GameData gameData) {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        String json = gson.toJson(gameData);
        editor.putString(CACHE, json);
        editor.apply();
    }

    public GameData loadCache() {
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
