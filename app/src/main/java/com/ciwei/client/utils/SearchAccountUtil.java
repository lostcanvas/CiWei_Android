package com.ciwei.client.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;

import com.ciwei.client.R;

/**
 * Created by Vernon on 15/3/9.
 */
public class SearchAccountUtil extends PopupWindow implements View.OnClickListener, AdapterView.OnItemClickListener, TextWatcher {

    private Context mContext; // 上下文

    public SearchAccountUtil(Context context) {
        super (context);
        this.mContext=context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate (R.layout.popu_search_account_action, null);

    }
    @Override
    public void onClick(View v){

    }

    @Override
    public void onItemClick(AdapterView<?> parent,View view,int position,long id){

    }

    @Override
    public void beforeTextChanged(CharSequence s,int start,int count,int after){

    }

    @Override
    public void onTextChanged(CharSequence s,int start,int before,int count){

    }

    @Override
    public void afterTextChanged(Editable s){

    }
}
