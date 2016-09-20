package com.smile.cookbook.imp;

import com.smile.cookbook.entity.FoodForTag;
import com.smile.cookbook.entity.FoodStyle;
import com.smile.cookbook.entity.SingleFood;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Smile on 2016/9/9.
 */
public interface RetrofitService {
    String BASE_URL="http://apicloud.mob.com/v1/cook/";


    @GET("category/query?")
    Call<FoodStyle> getFoodStyle(@QueryMap Map<String ,String> map);


    @GET("menu/search?")
    Call<FoodForTag> getFoodForTag(@QueryMap Map<String ,String> map);




    @GET("menu/query?")
    Call<SingleFood> getFoods(@QueryMap Map<String ,String> map);

}
