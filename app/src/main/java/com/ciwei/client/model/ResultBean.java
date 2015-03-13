package com.ciwei.client.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Vernon on 15/2/26.
 */
public class ResultBean extends BaseBean {

    private int            retCode;

    private ResultDataBean retData;

    public int getRetCode(){
        return retCode;
    }

    public void setRetCode(int retCode){
        this.retCode = retCode;
    }

    public ResultDataBean getRetData(){
        return retData;
    }

    public void setRetData(ResultDataBean retData){
        this.retData = retData;
    }
}
