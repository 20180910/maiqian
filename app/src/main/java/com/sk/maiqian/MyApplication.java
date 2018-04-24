package com.sk.maiqian;


import android.app.Application;
import android.content.Context;

import com.aspsine.multithreaddownload.DownloadConfiguration;
import com.aspsine.multithreaddownload.DownloadManager;
import com.github.androidtools.SPUtils;
import com.github.baseclass.view.Loading;
import com.github.retrofitutil.NetWorkManager;
import com.sdklibrary.base.share.qq.MyQQShare;
import com.sdklibrary.base.share.wx.MyWXShare;


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
        //正式
        String baseUrl="http://121.40.186.118:10088/";

        baseUrl="http://121.40.186.118:10089/";
        if(true&&BuildConfig.DEBUG){
            //测试
            baseUrl="http://121.40.186.118:10089/";
        }
        NetWorkManager.getInstance(getApplicationContext(),baseUrl,BuildConfig.DEBUG).complete();

        initDownloader();
        Loading.setLoadView(R.layout.app_loading_view);

        MyWXShare.setAppId(Config.weixing_id,Config.weixing_AppSecret);
        MyQQShare.setAppId(Config.qq_id);
    }
    private void initDownloader() {
        DownloadConfiguration configuration = new DownloadConfiguration();
        configuration.setMaxThreadNum(8);
        configuration.setThreadNum(2);
        DownloadManager.getInstance().init(getApplicationContext(), configuration);
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
