package com.ciwei.client.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.ciwei.client.R;
import com.ciwei.client.adapter.DragAdapter;
import com.ciwei.client.view.DragGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private List<HashMap<String, Object>> dataSourceList = new ArrayList<HashMap<String, Object>> ();
    private DragGridView                  dragGridView;
    private DragAdapter                   adapter;

    @Override
    protected int getLayoutResource(){
        return R.layout.activity_main;
    }

    @Override
    protected void initView(){
        dragGridView = (DragGridView) this.findViewById (R.id.dragGridView);
        dragGridView.setAdapter (new DragAdapter (MainActivity.this));
    }

    @Override
    protected void setListener(){

    }

    @Override
    protected void progressLogic(){

    }

    @Override
    protected void onClickEvent(View v){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater ().inflate (R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId ();
        if (id == R.id.action_settings) { return true; }
        return super.onOptionsItemSelected (item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent,View view,int position,long id){

    }
}
