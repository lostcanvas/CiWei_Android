package com.ciwei.client.db;

import android.content.Context;

import com.ciwei.client.model.AppAccount;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * l
 * 
 * @author zhangqiang Created by Vernon on 15/3/5.
 */
public class AppAccountManager {

    private static String            TAG = AppAccountManager.class.getSimpleName ();
    private Dao<AppAccount, Integer> dao;
    private DBHelper                 helper;
    private Context                  mContext;
    private static AppAccountManager manager;

    public AppAccountManager(Context mContext) throws SQLException {
        super ();
        this.mContext = mContext;
        helper = OpenHelperManager.getHelper (mContext, DBHelper.class);
        dao = helper.getAppAccountDao ();
    }

    /**
     * 获取单利
     * 
     * @param mContext
     * @return
     */
    public static AppAccountManager getInstance(Context mContext){
        if (manager == null) {
            try {
                manager = new AppAccountManager (mContext);
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
    public void insertList(List<AppAccount> list){
        for ( AppAccount bean : list ) {
            insert (bean);
        }
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<AppAccount> getALL(){
        List<AppAccount> list = new ArrayList<AppAccount> ();
        try {
            list = dao.queryForAll ();
        } catch (SQLException e) {
            e.printStackTrace ();
            list = null;
        }

        return list;

    }

    /**
     * 查询ids
     * 
     * @return
     */
    public List<Integer> getIds(){
        List<Integer> list = new ArrayList<> ();
        try {
            List<AppAccount> data = dao.queryForAll ();
            for ( AppAccount bean : data ) {
                list.add (Integer.parseInt (bean.getApp_id ()));
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return list;
    }

    /**
     * 清空数据
     *
     * @return
     */
    public void deleteALL(){
        try {
            TableUtils.clearTable (dao.getConnectionSource (), AppAccount.class);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    /**
     * 删除数据
     *
     * @return
     */
    public void deleteById(int id){
        try {
            dao.deleteById (id);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    /**
     * 插入
     *
     * @param bean
     */
    public void insert(AppAccount bean){
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
