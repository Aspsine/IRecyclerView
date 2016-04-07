package okhttp3;

import android.os.Handler;

import com.aspsine.irecyclerview.demo.network.NetworkAPI;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

/**
 * Created by aspsine on 16/4/7.
 */
public class GsonCallbackWrapper<T> implements okhttp3.Callback {

    NetworkAPI.Callback<T> mCallback;

    Handler mHandler;

    TypeToken<T> mTypeToken;

    public GsonCallbackWrapper(NetworkAPI.Callback<T> callback, Handler handler, TypeToken<T> typeToken) {
        this.mCallback = callback;
        this.mHandler = handler;
        this.mTypeToken = typeToken;
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            Gson gson = new Gson();
            final T t = gson.fromJson(responseBody.charStream(), mTypeToken.getType());
            deliverToMainThread(new Runnable() {
                @Override
                public void run() {
                    mCallback.onSuccess(t);
                }
            });
        }
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        deliverToMainThread(new Runnable() {
            @Override
            public void run() {
                mCallback.onFailure(e);
            }
        });
    }

    public void deliverToMainThread(Runnable runnable) {
        mHandler.post(runnable);
    }
}
