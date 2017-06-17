package com.donkamillo.gamedetails.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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

    static public void saveCache(GameData gameData, Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(gameData);

        editor.putString(CACHE, json);
        editor.apply();
    }

    static public GameData loadCache(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(CACHE, null);
        Type type = new TypeToken<GameData>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    static public void saveCacheDate(long date, Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putLong(CACHE_DATE, date);
        editor.apply();
    }

    static public Long loadCacheDate(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPrefs.getLong(CACHE_DATE, 0);
    }
}
