package com.ciwei.client.http;

/**
 * Created by Vernon on 15/2/26.
 */
public class HttpAPI {

    // 服务器地址
    public static final String SERVER_URL           = "http://121.41.85.236:3000";

    // 登录
    public static final String CIWEI_LOGIN          = SERVER_URL + "/system/login";
    // 获取验证码
    public static final String CIWEI_GET_VERIF_CODE = SERVER_URL + "/system/sms";
    public static final String CIWEI_REGISTER       = SERVER_URL + "/system/reg";
    // 获取Basedata数据
    public static final String CIWEI_BASEDATA       = SERVER_URL + "/system/baseData";
    // 账户
    public static final String CIWEI_GET_ACCOUNTS   = SERVER_URL + "/rest/getAccounts";
    // 检查token用户登录状态
    public static final String CIWEI_ACCESS         = SERVER_URL + "/system/access";
    // 获取app列表
    public static final String CIWEI_APPS           = SERVER_URL + "/system/apps";

    // 解除绑定账户
    public static final String CIWEI_DEL_ACCOUNT    = SERVER_URL + "/rest/delAccount";
    // 修改账户密码
    public static final String CIWEI_REST_CHPWD     = SERVER_URL + "/rest/chpwd";
}
