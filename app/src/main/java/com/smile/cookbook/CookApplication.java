package com.smile.cookbook;

import android.app.Application;
import android.content.Context;

/**
 * Created by Smile on 2016/9/14.
 */
public class CookApplication extends Application{
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
    }
    //返回
    public static Context getContextObject(){
        return mContext;
    }
}
