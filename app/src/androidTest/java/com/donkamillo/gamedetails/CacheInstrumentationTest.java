package com.donkamillo.gamedetails;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.donkamillo.gamedetails.data.local.SharedPreferencesManager;
import com.donkamillo.gamedetails.data.models.GameData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class CacheInstrumentationTest {

    private SharedPreferencesManager preferencesManager;

    @Before
    public void setUp() {
        Context app = InstrumentationRegistry.getTargetContext();
        this.preferencesManager = new SharedPreferencesManager(PreferenceManager.getDefaultSharedPreferences(app));
    }

    @Test
    public void saveLoadCacheDate() throws Exception {
        Date now = new Date();

        preferencesManager.saveCacheDate(now.getTime());
        long loadedDate = preferencesManager.loadCacheDate();

        assertEquals(now.getTime(), loadedDate);
    }

    @Test
    public void saveLoadCache_correct_data() throws Exception {
        preferencesManager.saveCache(getGameDataMock());
        GameData loadedCache = preferencesManager.loadCache();

        assertEquals(2, loadedCache.getData().size());
        assertEquals("d2", loadedCache.getData().get(1).getName());
        assertEquals("GBP", loadedCache.getCurrency());
    }

    @Test
    public void saveLoadCache_empty_data_list() throws Exception {
        GameData gameData = getGameDataMock();
        gameData.setData(null);

        preferencesManager.saveCache(gameData);
        GameData loadedCache = preferencesManager.loadCache();

        assertNull(loadedCache.getData());
        assertEquals("GBP", loadedCache.getCurrency());
    }

    @Test
    public void saveLoadCache_empty_currency() throws Exception {
        GameData gameData = getGameDataMock();
        gameData.setCurrency("");

        preferencesManager.saveCache(gameData);
        GameData loadedCache = preferencesManager.loadCache();

        assertEquals(2, loadedCache.getData().size());
        assertEquals("d2", loadedCache.getData().get(1).getName());
        assertEquals("", loadedCache.getCurrency());
    }

    private GameData getGameDataMock() {
        GameData gameData = new GameData();
        gameData.setCurrency("GBP");

        List<GameData.Data> dataList = new ArrayList<>();
        GameData.Data d1 = new GameData.Data();
        d1.setName("d1");
        d1.setJackpot(12345);
        dataList.add(d1);
        GameData.Data d2 = new GameData.Data();
        d2.setName("d2");
        d2.setJackpot(10000);
        dataList.add(d2);

        gameData.setData(dataList);
        return gameData;
    }
}
