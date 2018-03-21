package com.sk.maiqian;


import android.app.Application;
import android.content.Context;

import com.github.androidtools.SPUtils;
import com.github.baseclass.view.Loading;
import com.github.retrofitutil.NetWorkManager;


/**
 * Created by administartor on 2017/8/8.
 */

public class MyApplication extends Application {
 /*   @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }*/
    @Override
    public void onCreate() {
        super.onCreate();
        if(true&&BuildConfig.DEBUG){
            NetWorkManager.getInstance(getApplicationContext(),"http:/",BuildConfig.DEBUG).complete();
        }else{
            NetWorkManager.getInstance(getApplicationContext(),"http:/",BuildConfig.DEBUG).complete();
        }


        Loading.setLoadView(R.layout.app_loading_view);

    }

   //经度
   public static double longitude;//=121.432986;
    //纬度
   public static double latitude;//=31.229504;

    /**
     * 经度
     * @param context
     * @return
     */
    public static double getJingDu(Context context){
        if(longitude==0){
            longitude= SPUtils.getFloat(context,Config.longitude,0);
            return longitude;
        }else{
            return longitude;
        }
    }

    /**
     * 纬度
     * @param context
     * @return
     */
    public static double getWeiDu(Context context){
        if(latitude==0){
            latitude= SPUtils.getFloat(context,Config.latitude,0);
            return latitude;
        }else{
            return latitude;
        }
    }
    /**
     * 经度
     * @param context
     * @return
     */
    public static double Lng(Context context){
        if(longitude==0){
            longitude= SPUtils.getFloat(context,Config.longitude,0);
            return longitude;
        }else{
            return longitude;
        }
    }
    /**
     * 纬度
     * @param context
     * @return
     */
    public static double Lat(Context context){
        if(latitude==0){
            latitude= SPUtils.getFloat(context,Config.latitude,0);
            return latitude;
        }else{
            return latitude;
        }
    }
}
