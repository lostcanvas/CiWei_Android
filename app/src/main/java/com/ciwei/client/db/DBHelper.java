package com.ciwei.client.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.ciwei.client.model.App;
import com.ciwei.client.model.AppAccount;
import com.ciwei.client.model.BaseDataBean;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String        DBNAME    = "ciwei.db"; // 数据库名称
    private static final int           DBVERSION = 1;         // 数据库版本
    private Dao<BaseDataBean, Integer> searchDao;             // 搜索操作类
    private Dao<AppAccount, Integer>   appAccountDao;         // app账户操作类
    private Dao<App, Integer>          appDao;                // app

    // 要初始化的表
    public DBHelper(Context context, String databaseName, CursorFactory factory, int databaseVersion) {
        super (context, DBNAME, factory, DBVERSION);
    }

    public DBHelper(Context context) {
        // super(context, DATABASE_NAME, null, DATABASE_VERSION);
        super (context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0,ConnectionSource connectionSource){
        try {
            TableUtils.createTable (connectionSource, BaseDataBean.class);
            TableUtils.createTableIfNotExists (connectionSource, AppAccount.class);
            TableUtils.createTableIfNotExists (connectionSource, App.class);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase,ConnectionSource connectionSource,int oldVer,int newVer){
        try {
            TableUtils.createTableIfNotExists (connectionSource, BaseDataBean.class);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    /**
     * 获取账户数据库操作对象
     *
     * @return
     * @throws SQLException
     */
    public Dao<BaseDataBean, Integer> getBaseDataDao() throws SQLException{
        if (searchDao == null) {
            searchDao = getDao (BaseDataBean.class);
        }
        return searchDao;
    }

    public Dao<App, Integer> getAppDataDao() throws SQLException{
        if (appDao == null) {
            appDao = getDao (App.class);
        }
        return appDao;
    }

    /**
     * 获取账户数据库操作对象
     *
     * @return
     * @throws SQLException
     */
    public Dao<AppAccount, Integer> getAppAccountDao() throws SQLException{
        if (appAccountDao == null) {
            appAccountDao = getDao (AppAccount.class);
        }
        return appAccountDao;
    }

    @Override
    public void close(){
        searchDao = null;
        appAccountDao = null;
        appDao=null;
        super.close ();

    }
}
