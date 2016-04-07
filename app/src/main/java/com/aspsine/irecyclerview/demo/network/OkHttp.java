package com.aspsine.irecyclerview.demo.network;

import android.content.Context;

import com.aspsine.irecyclerview.demo.utils.FileUtils;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by aspsine on 16/4/7.
 */
public class OkHttp {
    private static final int MAX_CACHE_SIZE = 200 * 1024 * 1024;
    private static OkHttpClient sOkHttpClient;

    public static void init(Context context) {
        Context applicationContext = context.getApplicationContext();

        sOkHttpClient = new OkHttpClient.Builder()
                .cache(new Cache(FileUtils.getHttpCacheDir(applicationContext), MAX_CACHE_SIZE))
                .build();
    }

    public static OkHttpClient getOkHttpClient() {
        return sOkHttpClient;
    }
}
