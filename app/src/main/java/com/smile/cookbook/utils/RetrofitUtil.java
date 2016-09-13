package com.smile.cookbook.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smile.cookbook.entity.FoodStyle;
import com.smile.cookbook.entity.SingleFood;
import com.smile.cookbook.imp.ImpRequest;
import com.smile.cookbook.imp.RetrofitService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Smile on 2016/9/9.
 */
public class RetrofitUtil {

    private static CookClient sLgClient;
    private static RetrofitService sRetrofitService;
    private CookClient.LgBuilder mLgBuilder;
    private HashMap<String, Call> mRequestMap;

    public RetrofitUtil() {
        mRequestMap = new HashMap<>();
        mLgBuilder = new CookClient.LgBuilder();
    }

    //获取单例
    public static RetrofitUtil getInstance() {
        return RetrofitUtilHolder.sRetrofitUtil;
    }

    private static class RetrofitUtilHolder {
        private static final RetrofitUtil sRetrofitUtil = new RetrofitUtil();
    }

    public RetrofitUtil build() {
        sLgClient = mLgBuilder.build();
        sRetrofitService = sLgClient.getThemeService();
        return this;
    }

    /**
     * 设置读写超时
     *
     * @param timeout 超时时间
     *
     * @return
     */
    public RetrofitUtil setTimeout(int timeout) {
        if (mLgBuilder == null) {
            mLgBuilder = new CookClient.LgBuilder();
        }
        mLgBuilder.setTimeout(timeout);
        return this;
    }

    public RetrofitUtil setBaseUrl() {
        if (mLgBuilder == null) {
            mLgBuilder = new CookClient.LgBuilder();
        }
        mLgBuilder.setBaseUrl(getBaseUrl());
        return this;
    }

    public RetrofitUtil addFactory(){
        Gson gson = new GsonBuilder()
                //配置Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        if (mLgBuilder == null) {
            mLgBuilder = new CookClient.LgBuilder();
        }
       mLgBuilder.addConverterFactory(GsonConverterFactory.create(gson));
        return this;
    }

    /**
     * 根据类型获取对应的url
     *
     * @return
     */
    private String getBaseUrl() {
        return  RetrofitService.BASE_URL;
    }



    public void getFood(Map<String,String> map, final ImpRequest request){
        Call<SingleFood> call=sRetrofitService.getFoods(map);
        call.enqueue(new Callback<SingleFood>() {
            @Override
            public void onResponse(Call<SingleFood> call, Response<SingleFood> response) {
                SingleFood food=response.body();
                if(request!=null){
                    request.onSuccess(food);
                }else {
                    if(request==null){
                        request.onFailure();
                    }
                }
            }

            @Override
            public void onFailure(Call<SingleFood> call, Throwable t) {
                if (request!=null){
                    request.onFailure();
                }
            }
        });
    }

    public void getType(Map<String,String> map,final ImpRequest request){
        Call<FoodStyle> call = sRetrofitService.getFoodStyle(map);
        call.enqueue(new Callback<FoodStyle>() {
            @Override
            public void onResponse(Call<FoodStyle> call, Response<FoodStyle> response) {
                FoodStyle style=response.body();
                if(request!=null){
                    request.onSuccess(style);
                }else {
                    if(request==null){
                        request.onFailure();
                    }
                }
            }

            @Override
            public void onFailure(Call<FoodStyle> call, Throwable t) {
                if (request!=null){
                    request.onFailure();
                }
            }
        });
    }

}
