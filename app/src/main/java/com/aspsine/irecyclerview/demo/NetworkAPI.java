package com.aspsine.irecyclerview.demo;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by aspsine on 16/4/6.
 */
public class NetworkAPI {

    public static void requestImages(int page, final Callback<List<Image>> callback) {
        TypeToken<List<Image>> typeToken = new TypeToken<List<Image>>() {
        };
        String url = Constants.ImagesAPI(page);
        Request request = new GsonRequest<List<Image>>(url, typeToken, new Response.Listener<List<Image>>() {

            @Override
            public void onResponse(List<Image> response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error);
            }
        });
        App.getRequestQueue().add(request);
    }

    public static void requestBanners(final Callback<List<Image>> callback) {
        TypeToken<List<Image>> typeToken = new TypeToken<List<Image>>() {
        };
        String url = Constants.BannerAPI;
        Request request = new GsonRequest<List<Image>>(url, typeToken, new Response.Listener<List<Image>>() {

            @Override
            public void onResponse(List<Image> response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error);
            }
        });
        App.getRequestQueue().add(request);
    }

    public interface Callback<T> {
        void onSuccess(T t);

        void onFailure(Exception e);
    }
}
