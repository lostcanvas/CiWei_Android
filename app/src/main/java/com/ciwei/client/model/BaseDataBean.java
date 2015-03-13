package com.ciwei.client.model;

import com.ciwei.client.common.AppConfig;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Vernon on 15/3/4.
 */
@DatabaseTable(tableName = AppConfig.TABLE_BASE_DATA)
public class BaseDataBean extends BaseBean {
    @DatabaseField(id = true,canBeNull = false)
    private String fid;          // 用户名ID
    @DatabaseField
    private String name;
    @DatabaseField
    private String display;
    @DatabaseField
    private String placeholder;
    @DatabaseField
    private String multi_value;
    @DatabaseField
    private String format;
    @DatabaseField
    private String weight;
    @DatabaseField
    private String private_level;
    @DatabaseField
    private String status;
    @DatabaseField
    private String verify;


    public BaseDataBean() {
    }

    public String getFid(){
        return fid;
    }

    public void setFid(String fid){
        this.fid = fid;
    }

    public String getVerify(){
        return verify;
    }

    public void setVerify(String verify){
        this.verify = verify;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getPrivate_level(){
        return private_level;
    }

    public void setPrivate_level(String private_level){
        this.private_level = private_level;
    }

    public String getWeight(){
        return weight;
    }

    public void setWeight(String weight){
        this.weight = weight;
    }

    public String getFormat(){
        return format;
    }

    public void setFormat(String format){
        this.format = format;
    }

    public String getMulti_value(){
        return multi_value;
    }

    public void setMulti_value(String multi_value){
        this.multi_value = multi_value;
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

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
