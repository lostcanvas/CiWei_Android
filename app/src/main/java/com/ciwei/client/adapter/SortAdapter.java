package com.ciwei.client.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.ciwei.client.R;
import com.ciwei.client.model.SortModel;
import com.ciwei.client.utils.ViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

/**
 * @author绑定账户
 * Created by Vernon on 15/3/9.
 */
public class SortAdapter extends BaseAdapter implements SectionIndexer {

    private List<SortModel>     data;
    private Context             context;
    private ImageLoader         imageLoader;
    private DisplayImageOptions options;

    public SortAdapter(List<SortModel> data, Context context) {
        this.data = data;
        this.context = context;
        imageLoader = ImageLoader.getInstance ();
        options = new DisplayImageOptions.Builder ().showImageOnLoading (R.drawable.ic_launcher)
                .showImageForEmptyUri (R.drawable.ic_launcher)
                .showImageOnFail (R.drawable.ic_launcher)
                .cacheOnDisc (true)
                .cacheInMemory (true)
                .imageScaleType (ImageScaleType.EXACTLY)
                .bitmapConfig (Bitmap.Config.RGB_565)
                .considerExifParams (true)
                .build ();
    }
    @Override
    public int getCount(){
        return data.size ();
    }

    @Override
    public Object getItem(int position){
        return data.get (position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        if (convertView == null) {
            convertView = View.inflate (context, R.layout.adapter_bind_account_item, null);
        }
        TextView app_name = ViewHolder.get (convertView, R.id.adapter_appname_tx);
        TextView tvLetter = ViewHolder.get (convertView, R.id.catalog);
        TextView web_site = ViewHolder.get (convertView, R.id.adapter_web_site);
        ImageView appImg = ViewHolder.get (convertView, R.id.adapter_app_img);
        SortModel bean = data.get (position);
        imageLoader.displayImage (bean.getApp ().getLogo (), appImg, options);
        app_name.setText (bean.getName ());
        web_site.setText (bean.getApp ().getSlogan () == null ? "" : bean.getApp ().getSlogan ());
        // 根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition (position);
        // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection (section)) {
            tvLetter.setVisibility (View.VISIBLE);
            tvLetter.setText (bean.getSortLetters ());
        } else {
            tvLetter.setVisibility (View.GONE);
        }
        return convertView;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position){
        return data.get (position).getSortLetters ().charAt (0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section){
        for ( int i = 0 ; i < getCount () ; i++ ) {
            String sortStr = data.get (i).getSortLetters ();
            char firstChar = sortStr.toUpperCase ().charAt (0);
            if (firstChar == section) { return i; }
        }

        return -1;
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     * @param str
     * @return
     */
    private String getAlpha(String str){
        String sortStr = str.trim ().substring (0, 1).toUpperCase ();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches ("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections(){
        return null;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     * 
     * @param list
     */
    public void updateListView(List<SortModel> list){
        this.data = list;
        notifyDataSetChanged ();
    }
}
