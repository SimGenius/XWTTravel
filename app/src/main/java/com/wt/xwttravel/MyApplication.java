package com.wt.xwttravel;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.wt.xwttravel.util.ImageUtil;

/**
 * Created by Genius on 1/28/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setImageLoader();
        setFresco();
        PushManager.startWork(this,
                PushConstants.LOGIN_TYPE_API_KEY,
                "muf0VyVeBggRiG0MPRcIWlne");

    }

    void setImageLoader(){
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .diskCacheSize(1024*1048576)
                .memoryCache(new LruMemoryCache(30 * 1048576))
                .memoryCacheSize(30 * 1048576)
//                .writeDebugLogs()
                .build();


        ImageLoader.getInstance().init(configuration);
    }

    void setFresco(){
        Fresco.initialize(this);
    }
}
