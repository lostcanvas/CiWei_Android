package com.ciwei.client.model;

/**
 * 注册字段信息Bean
 * Created by Vernon on 15/3/6.
 */
public class RegFieldBean extends BaseDataBean {

    private String app_id;
    private String fid;
    private String fieldName;
    private String display;
    private String placeholder;
    private String format;

    @Override
    public String getFormat(){
        return format;
    }

    public String getApp_id(){
        return app_id;
    }

    public void setApp_id(String app_id){
        this.app_id = app_id;
    }

    @Override
    public void setFormat(String format){
        this.format = format;
    }

    @Override
    public String getPlaceholder(){
        return placeholder;
    }

    @Override
    public void setPlaceholder(String placeholder){
        this.placeholder = placeholder;
    }

    @Override
    public String getDisplay(){
        return display;
    }

    @Override
    public void setDisplay(String display){
        this.display = display;
    }

    public String getFieldName(){
        return fieldName;
    }

    public void setFieldName(String fieldName){
        this.fieldName = fieldName;
    }

    @Override
    public String getFid(){
        return fid;
    }

    @Override
    public void setFid(String fid){
        this.fid = fid;
    }
}
