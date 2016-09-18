package com.smile.cookbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.smile.cookbook.BaseActivity;
import com.smile.cookbook.CookApplication;
import com.smile.cookbook.MainActivity;
import com.smile.cookbook.R;
import com.smile.cookbook.config.Config;
import com.smile.cookbook.db.SQLController;
import com.smile.cookbook.entity.Food;
import com.smile.cookbook.entity.FoodStyle;
import com.smile.cookbook.imp.ImpRequest;
import com.smile.cookbook.utils.RetrofitUtil;
import com.smile.cookbook.utils.XLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 欢迎页面
 */
public class WecomeActivity extends BaseActivity {
    private List<Food> mFoods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mFoods= SQLController.getInstances(CookApplication.getContextObject()).getTypeInfo();
        XLog.e("dandy","woqu "+mFoods.size());
        if (mFoods==null||mFoods.size()<=0){
            Map<String ,String> map=new HashMap<>();
            map.put("key", Config.KEY);
            //XLog.e("dandy","eee"+map.get("key"));
            RetrofitUtil.getInstance().setBaseUrl().addFactory().setTimeout(10000).build().getFoodStyle(map, new ImpRequest() {
                @Override
                public void onSuccess(Object o) {
                    FoodStyle style= (FoodStyle) o;
                    // Log.e("dandy",""+style.toString());
                    List<FoodStyle.ResultBean.ChildsBean> childsBeen=style.getResult().getChilds();

                    SQLController.getInstances(CookApplication.getContextObject()).saveTypeInfo(childsBeen);
                    startActivity();
                }

                @Override
                public void onFailure() {
                    Log.e("dandy","请求失败");
                    startActivity();
                }
            });

        }else {
           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {
                   startActivity();
               }
           },2000);
        }

    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_wecome);
    }

    private void  startActivity(){
        Intent intent=new Intent(WecomeActivity.this, MainActivity.class);
        startActivity(intent);

        finish();
    }
}
