package com.ciwei.client.utils;

import android.annotation.TargetApi;
import android.os.Build;

import com.ciwei.client.model.Account;
import com.ciwei.client.model.UserBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vernon on 15/2/27.
 */
public class JsonParseUtil {

    private static final String TAG =JsonParseUtil.class.getSimpleName();

    public static UserBean getUserBean(JSONObject jsonObject){

        UserBean userBean = new UserBean ();
        try {
            userBean.setUid (jsonObject.getString ("uid"));
            userBean.setToken (jsonObject.getString ("token"));
        } catch (JSONException e) {
            e.printStackTrace ();
        }
        return userBean;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static List<Account> getAccounts(JSONObject jsonObject){
        List<Account> accounts = new ArrayList<Account>();
        try {
            JSONObject object = jsonObject.getJSONObject ("userFields");
            JSONArray jsonArray = object.getJSONArray ("2");
            Account account = new Account ();
            for ( int i = 0 ; i < jsonArray.length () ; i++ ) {
                String temp = (String) jsonArray.get (i);
                String[] arrays = temp.split ("\\|");
                account.setType("2");
                account.setAccount(arrays[0]);
                account.setCode(Integer.parseInt(arrays[1]));
                accounts.add (account);
            }
            JSONArray tempArray = object.getJSONArray ("3");
            for ( int i = 0 ; i < tempArray.length () ; i++ ) {
                String temp = (String) tempArray.get (i);
                String[] arrays = temp.split ("\\|");
                account.setType("3");
                account.setAccount(arrays[0]);
                account.setCode(Integer.parseInt(arrays[1]));
                accounts.add(account);
            }
        } catch (JSONException e) {
            e.printStackTrace ();
        }
        return accounts;
    }
}
