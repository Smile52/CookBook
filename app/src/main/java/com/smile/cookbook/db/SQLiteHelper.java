package com.smile.cookbook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**数据库创建类
 * Created by Smile on 2016/9/10.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "database.db";//数据库名o
    private static final int DB_VERSION = 1;            //版本号
    private static SQLiteHelper sSQLiteHelper;

    private static final String PT_PRIMARY = "PRIMARY KEY AUTOINCREMENT";//主键
    private static final String PT_INTEGER = "INTEGER";                     //integer类型
    private static final String PT_TEXT = "TEXT";                           //text类型

    public static  final String TYPE_TABLE_NAME="table_type";
    public static final String TYPE_ID="_id";//ID 主键
    public static final String TYPE_NAME="type_name";//类别总称
    public static final String TYPE_CHILD="type_child";//细分的小类别


    /**
     * 单例模式
     * @param context
     * @return
     */
    public static synchronized SQLiteHelper getInstance(Context context) {
        if (sSQLiteHelper == null) {
            synchronized (SQLiteHelper.class) {
                if (sSQLiteHelper == null) {
                    sSQLiteHelper = new SQLiteHelper(context);
                }
            }
        }
        return sSQLiteHelper;
    }
    private SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase database) {
        String type_sql="CREATE TABLE IF NOT EXISTS"+" "+TYPE_TABLE_NAME + "("
                + TYPE_ID + " " +PT_INTEGER + " "+PT_PRIMARY + ","
                + TYPE_NAME + " " +PT_TEXT + ","
                + TYPE_CHILD + " " +PT_TEXT +")";
        database.execSQL(type_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

    }
}
