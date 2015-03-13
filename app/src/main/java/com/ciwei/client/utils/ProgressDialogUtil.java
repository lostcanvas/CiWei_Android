package com.ciwei.client.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ciwei.client.R;

/**
 * @ClassName: ProgressUtil
 * @Title:
 * @Description:TODO(ProgressDialog的工具类)
 * @Author:wuzhen
 * @Since:2013-12-4下午10:58:13
 * @Version:1.0
 */
public final class ProgressDialogUtil {

    private static Dialog dialog = null;

    public static void showCustomProgressDialog(Context context,String msg){
        try {
            if (dialog == null || !dialog.isShowing ()) {
                dialog = new Dialog (context,R.style.dialog);
            }
            View view=View.inflate(context,R.layout.dialog_loading,null);
            TextView tv_msg= (TextView) view.findViewById(R.id.tv_msg);
            tv_msg.setText(msg);
            dialog.setContentView (R.layout.dialog_loading);
            dialog.setCancelable (true);
            dialog.show ();
        } catch (Exception e) {
            e.printStackTrace ();

        }
    }

    public static void dismiss(){
        try {
            if (dialog != null && dialog.isShowing ()) {
                dialog.dismiss ();
                dialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}
