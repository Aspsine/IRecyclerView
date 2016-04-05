package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;

/**
 * Created by Aspsine on 2015/9/7.
 */
public class GsonRequest<T> extends Request<T> {
    private Response.Listener<T> mListener;
    private Class<T> mClazz;
    private TypeToken<T> mTypeToken;
    private Gson mGson;

    public GsonRequest(String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(Method.GET, url, clazz, listener, errorListener);
    }

    public GsonRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.mClazz = clazz;
        this.mGson = new Gson();
    }

    public GsonRequest(String url, TypeToken<T> typeToken, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(Method.GET, url, typeToken, listener, errorListener);
    }

    public GsonRequest(int method, String url, TypeToken<T> typeToken, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.mTypeToken = typeToken;
        this.mGson = new Gson();
    }

    public GsonRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }

        final T t;
        if (mClazz != null && mTypeToken == null) {
            t = mGson.fromJson(parsed, mClazz);
        } else if (mClazz == null && mTypeToken != null) {
            t = mGson.fromJson(parsed, mTypeToken.getType());
        } else {
            throw new IllegalStateException("the type must be either class type or List type!");
        }
        return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T t) {
        mListener.onResponse(t);
    }
}
