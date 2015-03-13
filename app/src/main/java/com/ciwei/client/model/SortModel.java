package com.ciwei.client.model;

public class SortModel extends BaseBean {

    private AppAccount appAccount; // 个人账户
    private String     sortLetters; // 显示数据拼音的首字母
    private String     name;       // app名字
    private App        app;        // 应用
    private boolean    isBind;     // 是否绑定

    public boolean isBind(){
        return isBind;
    }

    public void setBind(boolean isBind){
        this.isBind = isBind;
    }

    public App getApp(){
        return app;
    }

    public void setApp(App app){
        this.app = app;
    }

    public String getSortLetters(){
        return sortLetters;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSortLetters(String sortLetters){
        this.sortLetters = sortLetters;
    }

    public AppAccount getAppAccount(){
        return appAccount;
    }

    public void setAppAccount(AppAccount appAccount){
        this.appAccount = appAccount;
    }
}
