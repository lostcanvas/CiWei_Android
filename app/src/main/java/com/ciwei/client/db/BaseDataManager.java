package com.ciwei.client.db;

import android.content.Context;

import com.ciwei.client.model.AppAccount;
import com.ciwei.client.model.BaseDataBean;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础数据增删改查 Created by Vernon on 15/3/4.
 */
public class BaseDataManager {

    private Dao<BaseDataBean, Integer> dao;
    private DBHelper                   helper;
    private Context                    mContext;
    private static BaseDataManager     manager;
    private static String              TAG = BaseDataManager.class.getSimpleName ();

    public BaseDataManager(Context mContext) throws SQLException {
        super ();
        this.mContext = mContext;
        helper = OpenHelperManager.getHelper (mContext, DBHelper.class);
        dao = helper.getBaseDataDao ();
    }

    /**
     * 获取单利
     *
     * @param mContext
     * @return
     */
    public static BaseDataManager getInstance(Context mContext){
        if (manager == null) {
            try {
                manager = new BaseDataManager (mContext);
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
    public void insertList(List<BaseDataBean> list){
        for ( BaseDataBean bean : list ) {
            insert (bean);
        }
    }

    /**
     * 插入
     *
     * @param bean
     */
    public void insert(BaseDataBean bean){
        try {
            dao.createOrUpdate (bean);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<BaseDataBean> getALL(){
        List<BaseDataBean> list = new ArrayList<BaseDataBean> ();
        try {
            list = dao.queryForAll ();
        } catch (SQLException e) {
            e.printStackTrace ();
            list = null;
        }

        return list;

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
