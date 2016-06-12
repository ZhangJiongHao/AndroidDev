/*
 * Android_Common. lastModified by bobomee at 2016.5.16 9:52
 *
 * Copyright (C) 2016 bobomee
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bobomee.android.common.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

/**
 * Created by bobomee on 16/5/16.
 */
public class AppUtil {

    public static Intent getInstallIntent(Uri uri) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(uri, "application/vnd.android.package-archive");
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return i;
    }

    /**
     * install apk file
     */
    public static void installApk(Context mContext, Uri uri) {
        Intent installIntent = getInstallIntent(uri);
        mContext.startActivity(installIntent);
    }

    /**
     * make marketscore
     */
    public static void MarketScore(Context context) {

        try {
            Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            Log.d("AppUtil", "Couldn't launch the market !");
        }

    }

    /**
     * 切换语言
     * @param context
     * @param locale
     */
    public static void switchLanguage(Context context, Locale locale) {
        Resources resources = context.getResources();// 获得res资源对象
        Configuration config = resources.getConfiguration();// 获得设置对象
        DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
        config.locale = locale; // 简体中文
        resources.updateConfiguration(config, dm);
    }


    /**
     * 获取系统当前的语言的国家
     *
     * @return CN 中国
     * EN english
     * *
     */
    public static String getCurrentLaunge(Context context) {
        // 获取系统默认的语言国家
        return context.getResources().getConfiguration().locale.getCountry();
    }

}
