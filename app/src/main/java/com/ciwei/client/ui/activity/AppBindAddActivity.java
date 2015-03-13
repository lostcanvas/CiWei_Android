package com.ciwei.client.ui.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.ciwei.client.R;
import com.ciwei.client.adapter.AppAdapter;
import com.ciwei.client.db.AppAccountManager;
import com.ciwei.client.db.AppManager;
import com.ciwei.client.model.App;
import com.ciwei.client.model.SortModel;
import com.ciwei.client.utils.PinyinComparator;
import com.ciwei.client.view.CharacterParser;
import com.ciwei.client.view.ClearEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhangqiang Created by Vernon on 15/3/12.
 */
public class AppBindAddActivity extends BaseActivity implements AdapterView.OnItemClickListener, TextWatcher {

    private ImageButton       btnAddView;

    private TextView          textTopTitle;

    private ClearEditText     search_clear_edit;

    private ListView          lv_account;

    private AppManager        appManager;

    private AppAccountManager appAccountManager;

    private List<SortModel>   sourceDataList = new ArrayList<> ();

    private AppAdapter        adapter;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser   characterParser;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator  pinyinComparator;

    @Override
    protected int getLayoutResource(){
        return R.layout.activity_add_app_account;
    }

    @Override
    protected void initView(){
        appManager = AppManager.getInstance (this);
        appAccountManager = AppAccountManager.getInstance (this);
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance ();
        pinyinComparator = new PinyinComparator ();
        search_clear_edit = (ClearEditText) this.findViewById (R.id.search_clear_edit);
        btnAddView = (ImageButton) findViewById (R.id.include_right_btn);
        textTopTitle = (TextView) this.findViewById (R.id.include_title_tx);
        lv_account = (ListView) this.findViewById (R.id.lv_account);
        textTopTitle.setText ("授权应用");
        btnAddView.setVisibility (View.GONE);
        addData ();
    }

    /**
     * 添加数据
     */
    private void addData(){
        List<App> data = appManager.getALL ();
        List<Integer> appids = appAccountManager.getIds ();
        for ( int i = 0 ; i < data.size () ; i++ ) {
            SortModel sortModel = new SortModel ();
            sortModel.setName (data.get (i).getAppName ());
            sortModel.setApp (data.get (i));
            int appid = Integer.parseInt (data.get (i).getAppId ());
            sortModel.setBind (appids.contains (appid));
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling (data.get (i).getAppName ());
            String sortString = pinyin.substring (0, 1).toUpperCase ();
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches ("[A-Z]")) {
                sortModel.setSortLetters (sortString.toUpperCase ());
            }
            sourceDataList.add (sortModel);
        }
        adapter = new AppAdapter (AppBindAddActivity.this,sourceDataList);
        lv_account.setAdapter (adapter);
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
        search_clear_edit.addTextChangedListener (this);
    }

    @Override
    protected void progressLogic(){

    }

    @Override
    protected void onClickEvent(View v){

    }

    @Override
    public void beforeTextChanged(CharSequence s,int start,int count,int after){

    }

    @Override
    public void onTextChanged(CharSequence s,int start,int before,int count){
        // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
        filterData (s.toString ());
    }

    @Override
    public void afterTextChanged(Editable s){

    }

    @Override
    public void onItemClick(AdapterView<?> parent,View view,int position,long id){

    }
}
