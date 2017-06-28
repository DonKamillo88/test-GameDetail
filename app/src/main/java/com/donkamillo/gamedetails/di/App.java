package com.donkamillo.gamedetails.di;

import android.app.Application;

/**
 * Created by DonKamillo on 28.06.2017.
 */

public class App extends Application {
    private static DaggerGraphComponent graph;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        buildComponentGraph();
    }

    public static DaggerGraphComponent component() {
        return graph;
    }

    public static void buildComponentGraph() {
        graph = DaggerGraphComponent.Initializer.init(instance);
    }
}
