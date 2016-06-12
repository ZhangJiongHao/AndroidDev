/*
 * Android_Common. lastModified by bobomee at 2016.5.16 9:49
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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bobomee.android.common.manager.ThreadManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author：BoBoMEe Created at 2016/1/18.
 */
public class BitmapUtil {

    /**
     * get bitmap from url
     */
    public static void getBitmap(final String url, final BitmapCallBack callBack) {
        ThreadManager.getLongPool().execute(new Runnable() {
            @Override
            public void run() {
                URL fileUrl = null;
                Bitmap bitmap = null;

                try {
                    fileUrl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                try {
                    HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                callBack.onComeolete(bitmap);
            }
        });

    }

    public interface BitmapCallBack {

        void onComeolete(Bitmap bitmap);
    }

    /**
     * get bitmap from storage
     */

    public static Bitmap getBitmapFromSD(String path, int width, int height) {

        Bitmap bitmap = null;
        //1先取得bitmap的size
        BitmapFactory.Options _option = new BitmapFactory.Options();
        _option.inJustDecodeBounds = true;
        // 通过这个bitmap获取图片的宽和高&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        BitmapFactory.decodeFile(path, _option);

        float realWidth = _option.outWidth;
        if(realWidth>width){
            _option.inSampleSize = (int) (realWidth/width);
        }
        _option.inDither = false; // Disable Dithering mode
        _option.inPurgeable = true; // Tell to gc that whether it needs free
        // memory, the Bitmap can be cleared
        _option.inInputShareable = true; // Which kind of reference will be used
        _option.inJustDecodeBounds = false;
        _option.inTempStorage = new byte[16 * 1024];
        _option.outHeight = height;
        _option.outWidth = width;

        try {
            bitmap = BitmapFactory.decodeFile(path, _option);
            if(bitmap==null){
                _option.inSampleSize = 10;
                bitmap = BitmapFactory.decodeFile(path, _option);
            }
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            _option.inSampleSize = 10;
            bitmap = BitmapFactory.decodeFile(path, _option);
            return bitmap;
        }

    }

}
