package com.aspsine.irecyclerview.demo.network;


import android.os.Handler;
import android.os.Looper;

import com.aspsine.irecyclerview.demo.Constants;
import com.aspsine.irecyclerview.demo.model.Image;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.GsonCallbackWrapper;
import okhttp3.Request;

/**
 * Created by aspsine on 16/4/6.
 */
public class NetworkAPI {

    private static final Handler sHandler = new Handler(Looper.getMainLooper());

    public static void requestImages(int page, final Callback<List<Image>> callback) {
        String url = Constants.ImagesAPI(page);
        final Request request = new Request.Builder().get().url(url).build();
        TypeToken<List<Image>> typeToken = new TypeToken<List<Image>>() {
        };
        OkHttp.getOkHttpClient().newCall(request).enqueue(new GsonCallbackWrapper<List<Image>>(callback, sHandler, typeToken));
    }

    public static void requestBanners(final Callback<List<Image>> callback) {
        String url = Constants.BannerAPI;
        final Request request = new Request.Builder().get().url(url).build();
        TypeToken<List<Image>> typeToken = new TypeToken<List<Image>>() {
        };
        OkHttp.getOkHttpClient().newCall(request).enqueue(new GsonCallbackWrapper<List<Image>>(callback, sHandler, typeToken));
    }

    public interface Callback<T> {
        void onSuccess(T t);

        void onFailure(Exception e);
    }
}
