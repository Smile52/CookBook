package com.smile.cookbook.utils;

import com.smile.cookbook.entity.FoodStyle;

/**常用工具类
 * Created by Smile on 2016/9/13.
 */
public class Utils {

    /**
     * 字符串截取出子菜品名字
     * @param bean
     * @return
     */
    public static String getTypeName(FoodStyle.ResultBean.ChildsBean bean){
        String name="";
        FoodStyle.ResultBean.ChildsBean.CategoryInfoBean infoBean=bean.getCategoryInfo();
        // Log.e("dandy","info "+infoBean.toString());
        name=infoBean.getName();

        name=name.substring(1,3);
        return name;
    }
}
