package com.ciwei.client.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.ciwei.client.R;
import com.ciwei.client.adapter.SortAdapter;
import com.ciwei.client.db.AppAccountManager;
import com.ciwei.client.db.AppManager;
import com.ciwei.client.model.App;
import com.ciwei.client.model.AppAccount;
import com.ciwei.client.model.SortModel;
import com.ciwei.client.utils.AndroidUtils;
import com.ciwei.client.utils.PinyinComparator;
import com.ciwei.client.view.CharacterParser;
import com.ciwei.client.view.ClearEditText;
import com.ciwei.client.view.SideBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.widget.AdapterView.OnItemClickListener;

/**
 * @author zhangqiang Created by Vernon on 15/3/8.
 */
public class BindedAccountActivity extends BaseActivity implements OnItemClickListener, TextWatcher {

    private static final String TAG            = BindedAccountActivity.class.getSimpleName ();
    private static final int    ACCOUNT_DETAIL = 1;                                           //
    private static final int    NONE           = 0;                                           //
    public static final String  EXTRA_KEY      = "app";
    private ImageButton         btn_back;

    private TextView            txtviewTitle;

    private ImageButton         imageBtnAdd;

    private ClearEditText       clearEditText;

    private SideBar             sideBar;

    private SortAdapter         adapter;

    private ListView            sortListView;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser     characterParser;
    private TextView            dialog;
    private List<SortModel>     sourceDataList;
    private AppAccountManager   accountManager;
    private AppManager          appManager;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator    pinyinComparator;

    @Override
    protected int getLayoutResource(){
        return R.layout.activity_binded_account;
    }

    @Override
    protected void initView(){
        accountManager = AppAccountManager.getInstance (this);
        appManager = AppManager.getInstance (this);

        btn_back = (ImageButton) this.findViewById (R.id.include_left_btn);
        btn_back.setVisibility (View.VISIBLE);
        txtviewTitle = (TextView) this.findViewById (R.id.include_title_tx);
        imageBtnAdd = (ImageButton) this.findViewById (R.id.include_right_btn);
        sortListView = (ListView) this.findViewById (R.id.lv_account);
        clearEditText = (ClearEditText) this.findViewById (R.id.search_clear_edit);
        sideBar = (SideBar) findViewById (R.id.sidrbar);
        dialog = (TextView) findViewById (R.id.dialog);
        sideBar = (SideBar) this.findViewById (R.id.sidrbar);
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance ();
        pinyinComparator = new PinyinComparator ();
        sideBar.setTextView (dialog);
        // 获取元数据
        sourceDataList = filledData (accountManager.getALL ());
        // 根据a-z进行排序源数据
        Collections.sort (sourceDataList, pinyinComparator);
        adapter = new SortAdapter (sourceDataList,BindedAccountActivity.this);
        sortListView.setAdapter (adapter);
        sideBar.setOnTouchingLetterChangedListener (new SideBar.OnTouchingLetterChangedListener () {

            @Override
            public void onTouchingLetterChanged(String s){
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection (s.charAt (0));
                if (position != -1) {
                    sortListView.setSelection (position);
                }
            }
        });
        txtviewTitle.setText ("已绑定账号");
    }

    /**
     * 为ListView填充数据
     *
     * @param
     * @return
     */
    private List<SortModel> filledData(List<AppAccount> data){
        List<SortModel> mSortList = new ArrayList<SortModel> ();
        for ( int i = 0 ; i < data.size () ; i++ ) {
            SortModel sortModel = new SortModel ();
            int app_id = Integer.parseInt (data.get (i).getApp_id ());
            App app = appManager.query (app_id);
            sortModel.setName (app.getAppName ());
            sortModel.setAppAccount (data.get (i));
            sortModel.setApp (app);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling (app.getAppName ());
            String sortString = pinyin.substring (0, 1).toUpperCase ();
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches ("[A-Z]")) {
                sortModel.setSortLetters (sortString.toUpperCase ());
            } else {
                sortModel.setSortLetters ("#");
            }
            mSortList.add (sortModel);
        }
        return mSortList;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==NONE){
            return;
        }

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * 
     * @param filterStr
     */
    private void filterData(String filterStr){
        List<SortModel> filterDateList = new ArrayList<SortModel> ();

        if (TextUtils.isEmpty (filterStr)) {
            filterDateList = sourceDataList;
        } else {
            filterDateList.clear ();
            for ( SortModel sortModel : sourceDataList ) {
                String name = sortModel.getName ();
                if (name.indexOf (filterStr.toString ()) != -1 || characterParser.getSelling (name).startsWith (filterStr.toString ())) {
                    filterDateList.add (sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort (filterDateList, pinyinComparator);
        adapter.updateListView (filterDateList);
    }

    @Override
    protected void setListener(){
        btn_back.setOnClickListener (this);
        sortListView.setOnItemClickListener (this);
        clearEditText.addTextChangedListener (this);
        imageBtnAdd.setOnClickListener (this);
    }

    @Override
    protected void progressLogic(){}

    @Override
    protected void onClickEvent(View v){
        switch (v.getId ()) {
            case R.id.include_left_btn:
                AndroidUtils.activityFinish (this);
                break;
            case R.id.include_right_btn:
                Intent intent=new Intent(this,AppBindAddActivity.class);
                AndroidUtils.startActivity(this,intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent,View view,int position,long id){
        SortModel bean = (SortModel) parent.getAdapter ().getItem (position);
        Intent i = new Intent (this,AccountDetailActivity.class);
        i.putExtra (EXTRA_KEY, (Serializable) bean);
        AndroidUtils.startActivityForResult (this, i, ACCOUNT_DETAIL);
    }

    @Override
    public void beforeTextChanged(CharSequence s,int start,int count,int after){}

    @Override
    public void onTextChanged(CharSequence s,int start,int before,int count){
        // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
        filterData (s.toString ());
    }

    @Override
    public void afterTextChanged(Editable s){

    }
}
