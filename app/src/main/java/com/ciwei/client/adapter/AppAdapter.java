package com.ciwei.client.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciwei.client.R;
import com.ciwei.client.model.SortModel;
import com.ciwei.client.utils.ViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by Vernon on 15/3/12.
 */
public class AppAdapter extends BaseAdapter {

    private Context             context;
    private List<SortModel>     data;
    private ImageLoader         imageLoader;
    private DisplayImageOptions options;

    public AppAdapter(Context context, List<SortModel> data) {
        this.context = context;
        this.data = data;
        imageLoader = ImageLoader.getInstance ();
        options = new DisplayImageOptions.Builder ().showImageOnLoading (R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(20))//
                .cacheOnDisc(true)
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
            convertView = View.inflate (context, R.layout.adapter_bind_app, null);
        }
        SortModel bean=data.get(position);
        ImageView appLogoImg = ViewHolder.get (convertView, R.id.app_logo);
        TextView appName = ViewHolder.get (convertView, R.id.tv_app_name);
        TextView appDescTextView = ViewHolder.get (convertView, R.id.app_desc);
        ImageView lockImg = ViewHolder.get (convertView, R.id.adapter_app_bind);

        imageLoader.displayImage(bean.getApp().getLogo(),appLogoImg,options);
        appName.setText(bean.getName());
        appDescTextView.setText(bean.getApp().getSlogan());
        lockImg.setVisibility(bean.isBind()?View.VISIBLE:View.GONE);

        return convertView;
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
