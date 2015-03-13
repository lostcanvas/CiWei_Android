package com.ciwei.client.model;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;

/**
 * Created by Vernon on 15/2/26.
 */
public class ResultDataBean extends BaseBean {

    private int    retCode;
    private String retData;

    public int getRetCode(){
        return retCode;
    }

    public void setRetCode(int retCode){
        this.retCode = retCode;
    }

    public String getRetData(){
        return retData;
    }

    public void setRetData(String retData){
        this.retData = retData;
    }

    /**
     * 请求是否成功
     *
     * @return
     */
    public boolean isSucess(){
        if (retCode == 200) {
            return true;
        } else {
            return false;
        }

    }

    // S_RetCode_Success = 200,
    // S_RetCode_Check_Sum_Error = 400,//:"success",
    // S_RetCode_Database_Error = 501,//:"database error",
    // S_RetCode_Params_Unmatched = 502,//:"params unmatched",
    // S_RetCode_Session_Not_Found = 503,//:"session not found",
    // S_RetCode_User_Not_Found = 504,//:"user not found",
    // S_RetCode_SMS_TEMP_ERROR = 505,//:"sms_temp_error",
    // S_RetCode_Verfied_Code_Expired = 506,//:"verified code expired",
    // S_RetCode_Verifid_Code_Not_Matched = 507,//:"verified code not matched",
    // S_RetCode_Tel_Has_Been_Registered = 508,//:"tel has been registered",
    // S_RetCode_QRtoken_Expired_or_not_matched = 509,//:"qrToken expired or not matched",
    // S_RetCode_Debug_Msg = 999//:"debug msg"
    /**
     * 获取错误信息
     *
     * @return
     */
    public String getMessage(){
        String message = "";
        com.alibaba.fastjson.JSONObject object = JSON.parseObject(retData);
        message = object.getString ("msg");
        switch (this.retCode) {
            case 400:
                break;
            case 501:
                break;
            case 502:
                break;
            case 503:
                break;
            case 504:
                break;
            case 505:
                break;
            case 506:
                break;
            case 507:
                break;
            case 508:
                break;
            case 509:
                break;
        }
        return message;
    }
}
