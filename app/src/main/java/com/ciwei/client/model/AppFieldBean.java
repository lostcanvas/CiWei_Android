package com.ciwei.client.model;

/**
 * regFields Created by Vernon on 15/3/5.
 */
public class AppFieldBean extends BaseBean {

    private String fieldName;
    private String display;
    private String placeholder;
    private String format;
    private String appid;

    public String getAppid(){
        return appid;
    }

    public void setAppid(String appid){
        this.appid = appid;
    }

    public String getFormat(){
        return format;
    }

    public void setFormat(String format){
        this.format = format;
    }

    public String getPlaceholder(){
        return placeholder;
    }

    public void setPlaceholder(String placeholder){
        this.placeholder = placeholder;
    }

    public String getDisplay(){
        return display;
    }

    public void setDisplay(String display){
        this.display = display;
    }

    public String getFieldName(){
        return fieldName;
    }

    public void setFieldName(String fieldName){
        this.fieldName = fieldName;
    }
}
