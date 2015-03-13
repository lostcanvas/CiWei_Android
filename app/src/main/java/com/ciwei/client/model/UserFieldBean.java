package com.ciwei.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vernon on 15/3/4.
 */
public class UserFieldBean {

    private List<String> username;
    private List<String> nickname;
    private List<String> tel;
    private List<String> email;
    private List<String> idCard;
    private String       gender;
    private String       birthday;
    private List<String> password;
    private String       realname;
    private String       uid;
    private String       token;

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getUid(){
        return uid;
    }

    public void setUid(String uid){
        this.uid = uid;
    }

    public List<String> getUsername(){
        return username;
    }

    public List<String> getNickname(){
        return nickname;
    }

    public void setNickname(List<String> nickname){
        this.nickname = nickname;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getRealname(){
        return realname;
    }

    public void setRealname(String realname){
        this.realname = realname;
    }

    public String getBirthday(){
        return birthday;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

    public List<String> getPassword(){
        return password;
    }

    public void setPassword(List<String> password){
        this.password = password;
    }

    public List<String> getIdCard(){
        return idCard;
    }

    public void setIdCard(List<String> idCard){
        this.idCard = idCard;
    }

    public List<String> getEmail(){
        return email;
    }

    public void setEmail(List<String> email){
        this.email = email;
    }

    public void setUsername(List<String> username){
        this.username = username;
    }

    public List<String> getTel(){
        return tel;
    }

    public void setTel(List<String> tel){
        this.tel = tel;
    }

    public List<Account> getAccounts(){
        List<Account> accounts = new ArrayList<Account> ();
        if (gender != null) {
            Account account = new Account ();
            String[] arrays = gender.split ("\\|");
            account.setType ("gender");
            account.setAccount (arrays[0]);
            account.setCode (Integer.parseInt (arrays[1]));
            accounts.add (account);
        }
        if (username != null) {
            for ( int i = 0 ; i < username.size () ; i++ ) {
                Account account = new Account ();
                String temp = username.get (i);
                String[] arrays = temp.split ("\\|");
                account.setType ("username");
                account.setAccount (arrays[0]);
                account.setCode (Integer.parseInt (arrays[1]));
                accounts.add (account);
            }
        }
        if (nickname != null) {
            for ( int i = 0 ; i < nickname.size () ; i++ ) {
                Account account = new Account ();
                String temp = nickname.get (i);
                String[] arrays = temp.split ("\\|");
                account.setType ("nickname");
                account.setAccount (arrays[0]);
                account.setCode (Integer.parseInt (arrays[1]));
                accounts.add (account);
            }
        }
        if (tel != null) {
            for ( int i = 0 ; i < tel.size () ; i++ ) {
                Account account = new Account ();
                String temp = tel.get (i);
                String[] arrays = temp.split ("\\|");
                account.setType ("tel");
                account.setAccount (arrays[0]);
                account.setCode (Integer.parseInt (arrays[1]));
                accounts.add (account);
            }
        }
        if (email != null) {
            for ( int i = 0 ; i < email.size () ; i++ ) {
                Account account = new Account ();
                String temp = email.get (i);
                String[] arrays = temp.split ("\\|");
                account.setType ("email");
                account.setAccount (arrays[0]);
                account.setCode (Integer.parseInt (arrays[1]));
                accounts.add (account);
            }
        }
        if (idCard != null) {
            for ( int i = 0 ; i < idCard.size () ; i++ ) {
                Account account = new Account ();
                String temp = idCard.get (i);
                String[] arrays = temp.split ("\\|");
                account.setType ("idCard");
                account.setAccount (arrays[0]);
                account.setCode (Integer.parseInt (arrays[1]));
                accounts.add (account);
            }
        }
        if (birthday != null) {
            Account account = new Account ();
            String[] arrays = birthday.split ("\\|");
            account.setType ("birthday");
            account.setAccount (arrays[0]);
            account.setCode (Integer.parseInt (arrays[1]));
            accounts.add (account);
        }
        if (password != null) {
            for ( int i = 0 ; i < password.size () ; i++ ) {
                Account account = new Account ();
                String temp = password.get (i);
                String[] arrays = temp.split ("\\|");
                account.setType ("password");
                account.setAccount (arrays[0]);
                account.setCode (Integer.parseInt (arrays[1]));
                accounts.add (account);
            }
        }
        if (realname != null) {
            Account account = new Account ();
            String[] arrays = realname.split ("\\|");
            account.setType ("realname");
            account.setAccount (arrays[0]);
            account.setCode (Integer.parseInt (arrays[1]));
            accounts.add (account);
        }
        return accounts;
    }

    public UserBean getUserBean(){
        UserBean userBean = new UserBean ();
        userBean.setToken (this.token);
        userBean.setUid (this.uid);
        return userBean;
    }
}
