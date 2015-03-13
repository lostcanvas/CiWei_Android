package com.ciwei.client.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtil {

    /**
     * 确认对话框
     * 
     * @param context
     * @param title
     * @param listener
     */
    public static void showConfirmDialog(Context context,String title,final ConfirmListener listener){
        new AlertDialog.Builder (context).setTitle ("温馨提示").setMessage (title).setPositiveButton ("确定", new DialogInterface.OnClickListener () {

            @Override
            public void onClick(DialogInterface dialog,int which){
                listener.sure ();
            }
        }).setNegativeButton ("取消", new DialogInterface.OnClickListener () {

            @Override
            public void onClick(DialogInterface dialog,int which){
                listener.cancle ();
                dialog.dismiss ();
            }
        }).show ();
    }

    /**
     * 确认对话框
     * 
     * @param context
     * @param title
     * @param listener
     */
    public static void showPlayDialog(Context context,String title,final ConfirmListener listener){
        new AlertDialog.Builder (context).setTitle ("温馨提示").setMessage (title).setPositiveButton ("继续", new DialogInterface.OnClickListener () {

            @Override
            public void onClick(DialogInterface dialog,int which){
                listener.sure ();
            }
        }).setNegativeButton ("暂停", new DialogInterface.OnClickListener () {

            @Override
            public void onClick(DialogInterface dialog,int which){
                listener.cancle ();

            }
        }).show ();
    }

    /**
     * 确认对话框
     * 
     * @param context
     * @param listener
     * @param title
     * @param sureBtnName
     * @param cancleBtnName
     * @param message
     */
    public static void showCustomDialog(Context context,final ConfirmListener listener,String title,String sureBtnName,String cancleBtnName,String message){
        new AlertDialog.Builder (context).setTitle (title).setMessage (message).setPositiveButton (sureBtnName, new DialogInterface.OnClickListener () {

            @Override
            public void onClick(DialogInterface dialog,int which){
                listener.sure ();
            }
        }).setNegativeButton (cancleBtnName, new DialogInterface.OnClickListener () {

            @Override
            public void onClick(DialogInterface dialog,int which){
                listener.cancle ();

            }
        }).show ();

    }

    /**
     * 对话框监听器
     */
    public interface ConfirmListener {

        void sure();

        void cancle();
    }

}
