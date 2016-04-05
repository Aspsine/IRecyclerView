package com.aspsine.irecyclerview.demo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by aspsine on 16/4/6.
 */
public class App extends Application {

    private static RequestQueue sRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        sRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getRequestQueue() {
        return sRequestQueue;
    }
}
