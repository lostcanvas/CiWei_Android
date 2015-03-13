package com.ciwei.client.model;

import com.ciwei.client.common.AppConfig;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author zhangqiang Created by Vernon on 15/3/7.
 */
@DatabaseTable(tableName = AppConfig.TABLE_APPS)
public class App extends BaseBean {

    private static final String ID_FIELD_NAME = "id";
    @DatabaseField(id = true,canBeNull = false)
    private String              appId;
    @DatabaseField
    private String              appName;
    @DatabaseField
    private String              appName_cn;
    @DatabaseField
    private String              logo;
    @DatabaseField
    private String              slogan;
//    @DatabaseField(generatedId = true,columnName = ID_FIELD_NAME)
//    private Integer             id;

    public App() {}

    public String getAppId(){
        return appId;
    }

    public void setAppId(String appId){
        this.appId = appId;
    }

//    public Integer getId(){
//        return id;
//    }
//
//    public void setId(Integer id){
//        this.id = id;
//    }

    public String getSlogan(){
        return slogan;
    }

    public void setSlogan(String slogan){
        this.slogan = slogan;
    }

    public String getLogo(){
        return logo;
    }

    public void setLogo(String logo){
        this.logo = logo;
    }

    public String getAppName_cn(){
        return appName_cn;
    }

    public void setAppName_cn(String appName_cn){
        this.appName_cn = appName_cn;
    }

    public String getAppName(){
        return appName;
    }

    public void setAppName(String appName){
        this.appName = appName;
    }
}
