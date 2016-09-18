package com.smile.cookbook.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smile.cookbook.entity.Food;
import com.smile.cookbook.entity.FoodStyle;
import com.smile.cookbook.utils.Utils;
import com.smile.cookbook.utils.XLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**数据库控制器 实现相关数据库操作
 * Created by Smile on 2016/9/13.
 */
public class SQLController {
    private static SQLController mController;
    private Context mContext;
    private static SQLiteHelper sSQLiteHelper;
    private SQLiteDatabase mDB;

    private SQLController(Context context) throws SQLiteException {
        mContext = context;
        sSQLiteHelper = SQLiteHelper.getInstance(context);
    }

    /**
     * 单例模式
     * @param context
     * @return
     */
    public static SQLController getInstances(Context context){

        if (mController==null){
            synchronized (SQLController.class){
                if (mController==null){
                    mController=new SQLController(context);
                }
            }
        }
        return mController;
    }

    /**
     * 保存菜品类别信息
     * @param childsBeen
     */
    public synchronized void saveTypeInfo(List<FoodStyle.ResultBean.ChildsBean> childsBeen){
        if (sSQLiteHelper==null||childsBeen==null){
            return;
        }
        String sql="INSERT INTO "+SQLiteHelper.TYPE_TABLE_NAME+ " ("+SQLiteHelper.TYPE_NAME +", "+ SQLiteHelper.TYPE_CHILD+  ") VALUES(?,?)";
        mDB=sSQLiteHelper.getWritableDatabase();
        mDB.beginTransaction();

        SQLiteStatement stmt=mDB.compileStatement(sql);
        for(FoodStyle.ResultBean.ChildsBean bean:childsBeen){
          //  XLog.e("dandy","bean "+bean.getChilds().toString());
          //  stmt.bindString(1,bean.getCategoryInfo().toString());
            stmt.bindString(1, Utils.getTypeName(bean));
            stmt.bindString(2,jsonToString(bean));
            stmt.execute();
            stmt.clearBindings();
        }
        mDB.setTransactionSuccessful();
        mDB.endTransaction();
        mDB.close();
    }

    /**
     * 获取菜品信息
     * @return
     */
    public synchronized List<Food>  getTypeInfo(){
        List<Food> beanList=new ArrayList<>();
        mDB=sSQLiteHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor=mDB.query(SQLiteHelper.TYPE_TABLE_NAME,null,null,null,null,null,null);
            while (cursor.moveToNext()){
                Food food=new Food();
                food.setName(cursor.getString(1));
                food.setChildList(getObject(cursor.getString(2)));

                beanList.add(food);
            }
        }catch (Exception e){
            cursor.close();
        }
        return beanList;
    }

    private List<Food.ChildBean> getObject(String json){
       // XLog.e("dandy","  "+json);
        List<Food.ChildBean> list=null;
        Gson gson=new Gson();
        list=gson.fromJson(json,new TypeToken<List<Food.ChildBean.CategoryInfoBean>>(){}.getType());

        XLog.e("dandy","list "+list.toString() );

        return list;
    }

    /**
     * 将json转换成字符串保存到本地来
     * @param childsBeen
     * @return
     */
    private String jsonToString(FoodStyle.ResultBean.ChildsBean childsBeen){
        JSONArray array=new JSONArray();
        int count =childsBeen.getChilds().size();
        try {
        for (int i=0;i<count;i++){
            FoodStyle.ResultBean.ChildsBean.ChildBean bean=childsBeen.getChilds().get(i);
            JSONObject object=new JSONObject();

            object.put("ctgId",bean.getCategoryInfo().getCtgId());
            object.put("name",bean.getCategoryInfo().getName());
            object.put("parentId",bean.getCategoryInfo().getParentId());
            array.put(object);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }


        return array.toString();
    }
}
