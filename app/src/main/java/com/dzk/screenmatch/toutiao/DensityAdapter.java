package com.dzk.screenmatch.toutiao;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
/**
 * 屏幕密度densityDpi = Math.sqrt(screenWithPx * screenWidthPx + screenHeightPx * screenHeightPx)/inch(屏幕尺寸);
 * 160dp为基准单位
 * float density = densityDpi / 160;
 * px = dp * density;
 */

/**
 * @author jackie
 * @date 2020/11/11
 */
public class DensityAdapter {
    /**
     * 系统的density
     */
    private static float sNonCompatDensity;
    /**
     * 系统的ScaleDensity
     */
    private static float sNonCompatScaleDensity;

    /**
     * @param activity
     * @param application
     * @param designedWidth 设计图屏幕的宽度,单位是dp
     */
    public static void setAdapteredDensity(Activity activity, Application application,int designedWidth){
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        if (sNonCompatDensity == 0){
            sNonCompatDensity = appDisplayMetrics.density;
            sNonCompatScaleDensity = appDisplayMetrics.scaledDensity;
            //监听系统中设置字体
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(@NonNull Configuration configuration) {
                    if (configuration != null && configuration.fontScale > 0){
                        sNonCompatScaleDensity = appDisplayMetrics.scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
            //密度
            final float targetDensity = appDisplayMetrics.widthPixels / designedWidth;
            //字体缩放
            final float targetScaleDensity = targetDensity * (sNonCompatScaleDensity / sNonCompatDensity);
            //屏幕密度
            final int targetDensityDpi = (int) (targetDensity *160);

            appDisplayMetrics.density = targetDensity;
            appDisplayMetrics.scaledDensity = targetScaleDensity;
            appDisplayMetrics.densityDpi = targetDensityDpi;

            final DisplayMetrics activityDisplayMetric = activity.getResources().getDisplayMetrics();
            activityDisplayMetric.density = targetDensity;
            activityDisplayMetric.scaledDensity = targetScaleDensity;
            activityDisplayMetric.densityDpi = targetDensityDpi;
        }
    }
} 
