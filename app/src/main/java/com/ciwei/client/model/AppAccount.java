package com.ciwei.client.model;

import com.ciwei.client.common.AppConfig;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Vernon on 15/3/5.
 */
@DatabaseTable(tableName = AppConfig.TABLE_APP_ACCOUNT)
public class AppAccount extends BaseBean {
    public final static String ID_FIELD_NAME = "account_id";
    @DatabaseField
    private String u;
    @DatabaseField
    private String app_id;
    @DatabaseField
    private String p;
    @DatabaseField(generatedId=true,columnName=ID_FIELD_NAME)
    private Integer id;


    public AppAccount() {
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getU(){
        return u;
    }

    public void setU(String u){
        this.u = u;
    }

    public String getP(){
        return p;
    }

    public void setP(String p){
        this.p = p;
    }
}
