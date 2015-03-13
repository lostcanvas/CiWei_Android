package com.ciwei.client.model;

/**
 * Created by Vernon on 15/2/27.
 */
public class Account extends BaseBean {

    private String account;
    private String type;    //
    private int    code;    // 1已验证，0未验证

    public String getAccount(){
        return account;
    }

    public void setAccount(String account){
        this.account = account;
    }

    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }
}
