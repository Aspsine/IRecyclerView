package com.aspsine.irecyclerview.demo.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by aspsine on 15-3-13.
 */
public class DensityUtils {

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getScreenHeightWithDecorations(Context context) {
        int heightPixels;
        WindowManager w = ((Activity) context).getWindowManager();
        Display d = w.getDefaultDisplay();
        android.graphics.Point realSize = new android.graphics.Point();
        try {
            Display.class.getMethod("getRealSize", android.graphics.Point.class).invoke(d, realSize);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        heightPixels = realSize.y;
        return heightPixels;
    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
