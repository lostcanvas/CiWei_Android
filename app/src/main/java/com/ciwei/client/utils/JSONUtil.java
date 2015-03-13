package com.ciwei.client.utils;

import com.alibaba.fastjson.JSON;
import com.ciwei.client.model.App;
import com.ciwei.client.model.AppAccount;
import com.ciwei.client.model.BaseDataBean;
import com.ciwei.client.model.RegFieldBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JSONUtil {

    private static final String TAG = JSONUtil.class.getSimpleName ();

    // 自定义一个方法解析
    // public static List<?> getPerson(String key,String jsonString){
    // List<Person> persons=new ArrayList<Person>();
    // try {
    // //获取Json对象
    // JSONObject jsonObj=new JSONObject(jsonString);
    // //由Json对象获取JsonArray
    // JSONArray jsonArr=jsonObj.getJSONArray(key);
    // //遍历数组获得每一个jsonObject对象
    // for (int i = 0; i < jsonArr.length(); i++) {
    // JSONObject personObj=jsonArr.getJSONObject(i);
    // int id=personObj.getInt("id");
    // String name=personObj.getString("name");
    // int age=personObj.getInt("age");
    // Person p=new Person(id,name,age);
    // persons.add(p);
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return persons;
    // }
    public static int getStatusCode(String jsonString){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject (jsonString);
            return jsonObject.getInt ("retCode");
        } catch (JSONException e) {
            e.printStackTrace ();
        }
        return 0;
    }

    public static JSONObject getRetData(String jsonString){
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject (jsonString).getJSONObject ("retData");
        } catch (JSONException e) {
            return null;
        }
        return jsonObj;
    }

    public static List<AppAccount> getAppAccount(String jsonstring){
        com.alibaba.fastjson.JSONObject object1 = JSON.parseObject (jsonstring);
        String temp = object1.getString ("accounts");
        com.alibaba.fastjson.JSONObject object2 = JSON.parseObject (temp);
        Set<String> keys = object2.keySet ();
        Iterator<String> it = keys.iterator ();
        List<AppAccount> accounts = new ArrayList<AppAccount> ();
        while (it.hasNext ()) {
            String str = it.next ();
            List<AppAccount> appAccounts = JSON.parseArray (object2.getString (str), AppAccount.class);
            for ( int i = 0 ; i < appAccounts.size () ; i++ ) {
                appAccounts.get (i).setApp_id (str);
            }
            accounts.addAll (appAccounts);
        }
        return accounts;
    }

    /***
     * 解析apps数据
     * 
     * @param jsonstr
     * @return
     */
    public static List<App> getApps(String jsonstr){
        com.alibaba.fastjson.JSONObject object1 = JSON.parseObject (jsonstr);
        com.alibaba.fastjson.JSONObject object2 = JSON.parseObject (object1.getString ("apps"));
        Set<String> keys = object2.keySet ();
        Iterator<String> it = keys.iterator ();
        List<App> apps = new ArrayList<App> ();
        while (it.hasNext ()) {
            String str = it.next ();
            App app = JSON.parseObject (object2.getString (str), App.class);
            apps.add (app);
        }
        return apps;
    }

    public static List<RegFieldBean> getRegFields(String jsonstring){
        com.alibaba.fastjson.JSONObject object1 = JSON.parseObject (jsonstring);
        String temp = object1.getString ("regFields");
        com.alibaba.fastjson.JSONObject object2 = JSON.parseObject (temp);
        Set<String> keys = object2.keySet ();
        Iterator<String> it = keys.iterator ();
        List<RegFieldBean> fields = new ArrayList<RegFieldBean> ();
        while (it.hasNext ()) {
            String str = it.next ();
            List<RegFieldBean> regFileds = JSON.parseArray (object2.getString (str), RegFieldBean.class);
            for ( int i = 0 ; i < regFileds.size () ; i++ ) {
                regFileds.get (i).setApp_id (str);
            }
            fields.addAll (regFileds);
        }
        return fields;
    }

    /**
     * get String data param
     *
     * @return int data
     * @throws JSONException
     */
    public static int getInt(String key,String jsonString){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject (jsonString);
            return jsonObject.getInt (key);
        } catch (JSONException e) {
            e.printStackTrace ();
        }
        return 0;
    }
    // 解析多个数据的Json
    // privatevoidparseJsonMulti(StringstrResult){
    // try{
    // JSONArrayjsonObjs=newJSONObject(strResult).getJSONArray("singers");
    // Strings="";
    // for(inti=0;i<jsonObjs.length();i++){
    // JSONObjectjsonObj=((JSONObject)jsonObjs.opt(i))
    // .getJSONObject("singer");
    // intid=jsonObj.getInt("id");
    // Stringname=jsonObj.getString("name");
    // Stringgender=jsonObj.getString("gender");
    // s+= "ID号"+id+",姓名："+name+",性别："+gender+"\n";
    // }
    // tvJson.setText(s);
    // }catch(JSONExceptione){
    // System.out.println("Jsonsparseerror!");
    // e.printStackTrace();
    // }
    // }
}
