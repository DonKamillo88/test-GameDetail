package com.donkamillo.gamedetails.di;

import android.app.Application;

import com.donkamillo.gamedetails.di.module.DataModule;
import com.donkamillo.gamedetails.di.module.MainModule;

/**
 * Created by DonKamillo on 28.06.2017.
 */

public class App extends Application {
    private static AppComponent graph;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        buildComponentGraph();
    }

    public static AppComponent component() {
        return graph;
    }

    public static void buildComponentGraph() {
        graph = DaggerAppComponent.builder()
                .dataModule(new DataModule(instance))
                .mainModule(new MainModule(instance))
                .build();
    }


}
