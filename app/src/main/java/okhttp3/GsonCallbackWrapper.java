package okhttp3;

import android.os.Handler;
import android.os.Looper;

import com.aspsine.irecyclerview.demo.network.NetworkAPI;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

/**
 * Created by aspsine on 16/4/7.
 */
public class GsonCallbackWrapper<T> implements okhttp3.Callback {

    private static final Handler sHandler = new Handler(Looper.getMainLooper());

    private static final Gson sGson = new Gson();

    private NetworkAPI.Callback<T> mCallback;

    private TypeToken<T> mTypeToken;

    /**
     * @param callback  callback to be wrapped
     * @param typeToken why we need a TypeToken instance? see: https://github.com/google/gson/issues/828
     */
    public GsonCallbackWrapper(NetworkAPI.Callback<T> callback, TypeToken<T> typeToken) {
        this.mCallback = callback;
        this.mTypeToken = typeToken;
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            final T t = sGson.getAdapter(mTypeToken).fromJson(responseBody.charStream());
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
        sHandler.post(runnable);
    }
}
