package com.ciwei.client.model;

import java.util.List;

/**
 * @author zhangqiang Created by Vernon on 15/2/26.
 */
public class UserBean extends BaseBean {

    private String        token;
    private String        uid;
    private String        tel;
    private String        password;
    private List<Account> accounts;

    public String getTel(){
        return tel;
    }

    public void setTel(String tel){
        this.tel = tel;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public List<Account> getAccounts(){
        return accounts;
    }

    public void setAccounts(List<Account> accounts){
        this.accounts = accounts;
    }

    public String getUid(){
        return uid;
    }

    public void setUid(String uid){
        this.uid = uid;
    }
}
