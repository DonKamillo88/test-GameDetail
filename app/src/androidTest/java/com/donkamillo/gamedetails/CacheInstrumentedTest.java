package com.donkamillo.gamedetails;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.donkamillo.gamedetails.data.local.SharedPreferencesManager;
import com.donkamillo.gamedetails.data.models.GameData;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CacheInstrumentedTest {

    @Test
    public void saveLoadCacheDate() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        Date now = new Date();

        SharedPreferencesManager.saveCacheDate(now.getTime(), appContext);
        long loadedDate = SharedPreferencesManager.loadCacheDate(appContext);

        assertEquals(now.getTime(), loadedDate);
    }

    @Test
    public void saveLoadCache_correct_data() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        SharedPreferencesManager.saveCache(getGameDataMock(), appContext);
        GameData loadedCache = SharedPreferencesManager.loadCache(appContext);

        assertEquals(2, loadedCache.getData().size());
        assertEquals("d2", loadedCache.getData().get(1).getName());
        assertEquals("GBP", loadedCache.getCurrency());
    }

    @Test
    public void saveLoadCache_empty_data_list() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        GameData gameData = getGameDataMock();
        gameData.setData(null);

        SharedPreferencesManager.saveCache(gameData, appContext);
        GameData loadedCache = SharedPreferencesManager.loadCache(appContext);

        assertNull(loadedCache.getData());
        assertEquals("GBP", loadedCache.getCurrency());
    }

    @Test
    public void saveLoadCache_empty_currency() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        GameData gameData = getGameDataMock();
        gameData.setCurrency("");

        SharedPreferencesManager.saveCache(gameData, appContext);
        GameData loadedCache = SharedPreferencesManager.loadCache(appContext);

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
