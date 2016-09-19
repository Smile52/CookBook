package com.smile.cookbook.control;

import android.content.Context;
import android.util.Log;

import com.smile.cookbook.config.Config;
import com.smile.cookbook.db.SQLController;
import com.smile.cookbook.entity.Food;
import com.smile.cookbook.entity.FoodStyle;
import com.smile.cookbook.imp.ImpRequest;
import com.smile.cookbook.utils.RetrofitUtil;
import com.smile.cookbook.utils.XLog;
import com.smile.cookbook.view.PagerItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**标签控制器
 * Created by Smile on 2016/9/10.
 */
public class TabController {
    private Context mContext;
    private List<Food> mFoods;
    public TabController(Context context) {
        super();
        mContext=context;


    }

    public void get(){
        Map<String ,String> map=new HashMap<>();
        map.put("key", Config.KEY);
        XLog.e("dandy","eee"+map.get("key"));
        RetrofitUtil.getInstance().setBaseUrl().addFactory().setTimeout(10000).build().getFoodStyle(map, new ImpRequest() {
            @Override
            public void onSuccess(Object o) {
                FoodStyle style= (FoodStyle) o;
                // Log.e("dandy",""+style.toString());
                List<FoodStyle.ResultBean.ChildsBean> childsBeen=style.getResult().getChilds();

                SQLController.getInstances(mContext).saveTypeInfo(childsBeen);

            }

            @Override
            public void onFailure() {
                Log.e("dandy","请求失败");
            }
        });
    }

    public List<PagerItem> setTabData(){
        List<PagerItem> itemList=new ArrayList<>();

        mFoods=SQLController.getInstances(mContext).getTypeInfo();
        //XLog.e("dandy","www"+mFoods.size());
        for (Food food : mFoods){
            itemList.add(new PagerItem(food.getName(),food.getChildList()));
            //XLog.e("dandy"," "+food.toString());
        }
        return itemList;
    }


}
