package com.smile.cookbook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;

import com.smile.cookbook.entity.FoodStyle;
import com.smile.cookbook.utils.Utils;

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
    public void saveTypeInfo(List<FoodStyle.ResultBean.ChildsBean> childsBeen){
        if (sSQLiteHelper==null||childsBeen==null){
            return;
        }
        String sql="INSERT INTO "+SQLiteHelper.TYPE_TABLE_NAME+ " ("+SQLiteHelper.TYPE_NAME +", "+ SQLiteHelper.TYPE_CHILD+  ") VALUES(?,?)";
        mDB=sSQLiteHelper.getWritableDatabase();
        mDB.beginTransaction();
        SQLiteStatement stmt=mDB.compileStatement(sql);
        for(FoodStyle.ResultBean.ChildsBean bean:childsBeen){
          //  stmt.bindString(1,bean.getCategoryInfo().toString());
            stmt.bindString(1, Utils.getTypeName(bean));
            stmt.bindString(2,bean.getChilds().toString());
            stmt.execute();
            stmt.clearBindings();
        }
        mDB.setTransactionSuccessful();
        mDB.endTransaction();
        mDB.close();

    }


}
