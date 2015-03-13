package com.ciwei.client.model;

/**
 * Created by Vernon on 15/3/4.
 */
public class FieldBean {

    private BaseDataBean username; // 用户名
    private BaseDataBean tel;     // 电话
    private BaseDataBean email;   // 邮件
    private BaseDataBean idCard;  // 身份卡
    private BaseDataBean nickname; // 昵称
    private BaseDataBean gender;  // 性别
    private BaseDataBean birthday; // 生日
    private BaseDataBean password; // 密码
    private BaseDataBean realname; // 真实名字

    private int          apps;    // 应用数量

    public BaseDataBean getUsername(){
        return username;
    }

    public void setUsername(BaseDataBean username){
        this.username = username;
    }

    public BaseDataBean getRealname(){
        return realname;
    }

    public void setRealname(BaseDataBean realname){
        this.realname = realname;
    }

    public BaseDataBean getPassword(){
        return password;
    }

    public void setPassword(BaseDataBean password){
        this.password = password;
    }

    public BaseDataBean getBirthday(){
        return birthday;
    }

    public void setBirthday(BaseDataBean birthday){
        this.birthday = birthday;
    }

    public BaseDataBean getGender(){
        return gender;
    }

    public void setGender(BaseDataBean gender){
        this.gender = gender;
    }

    public BaseDataBean getIdCard(){
        return idCard;
    }

    public void setIdCard(BaseDataBean idCard){
        this.idCard = idCard;
    }

    public BaseDataBean getNickname(){
        return nickname;
    }

    public void setNickname(BaseDataBean nickname){
        this.nickname = nickname;
    }

    public BaseDataBean getEmail(){
        return email;
    }

    public void setEmail(BaseDataBean email){
        this.email = email;
    }

    public BaseDataBean getTel(){
        return tel;
    }

    public int getApps(){
        return apps;
    }

    public void setApps(int apps){
        this.apps = apps;
    }

    public void setTel(BaseDataBean tel){
        this.tel = tel;
    }
}
