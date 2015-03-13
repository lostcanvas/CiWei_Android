package com.ciwei.client.utils;

import java.util.Random;

/**
 * Created by Vernon on 15/3/12.
 */
public class RadomStirngUtil {

    public static String getRandomString(int length){ // length表示生成字符串的长度
        String base = "QWERTYUIOPASDFGHJKLZXCVBNMabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random ();
        StringBuffer sb = new StringBuffer ();
        for ( int i = 0 ; i < length ; i++ ) {
            int number = random.nextInt (base.length ());
            sb.append (base.charAt (number));
        }
        return sb.toString ();
    }

}
