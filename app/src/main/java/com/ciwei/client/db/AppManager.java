package com.ciwei.client.db;

import android.content.Context;

import com.ciwei.client.model.App;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vernon on 15/3/7.
 */
public class AppManager {

    private static String     TAG = AppManager.class.getSimpleName ();
    private Dao<App, Integer> dao;
    private DBHelper          helper;
    private Context           mContext;
    private static AppManager manager;

    public AppManager(Context mContext) throws SQLException {
        super ();
        this.mContext = mContext;
        helper = OpenHelperManager.getHelper (mContext, DBHelper.class);
        dao = helper.getAppDataDao ();
    }

    /**
     * 获取单利
     *
     * @param mContext
     * @return
     */
    public static AppManager getInstance(Context mContext){
        if (manager == null) {
            try {
                manager = new AppManager (mContext);
            } catch (SQLException e) {
                e.printStackTrace ();
                manager = null;
            }
        }
        return manager;
    }

    /**
     * 批量插入
     *
     * @param list
     */
    public void insertList(List<App> list){
        for ( App bean : list ) {
            insert (bean);
        }
    }

    /**
     * 通过ID查询
     *
     * @param
     * @return
     */
    public App query(int id){
        App bean = null;
        try {
            bean = dao.queryForId (id);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return bean;
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<App> getALL(){
        List<App> list = new ArrayList<App> ();
        try {
            list = dao.queryForAll ();
        } catch (SQLException e) {
            e.printStackTrace ();
            list = null;
        }

        return list;

    }

    private void insert(App bean){
        try {
            dao.createOrUpdate (bean);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    /**
     * 释放内存
     */
    public void release(){
        if (helper != null) {
            OpenHelperManager.releaseHelper ();
        }
        helper = null;
        // may be has error
        manager = null;
    }
}
