package com.aspsine.irecyclerview.demo.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by aspsine on 16/4/7.
 */
public class FileUtils {

    private static final String HTTP_CACHE_DIR = "http";

    public static final File getHttpCacheDir(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return new File(context.getExternalCacheDir(), HTTP_CACHE_DIR);
        }
        return new File(context.getCacheDir(), HTTP_CACHE_DIR);
    }
}
