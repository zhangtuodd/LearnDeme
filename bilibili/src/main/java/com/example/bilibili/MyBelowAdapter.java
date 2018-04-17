package com.example.bilibili;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;

import java.util.List;

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2018/4/14
 */

public class MyBelowAdapter extends BaseAdapter {
    private Context context;
    private PoiInfo info;

    public MyBelowAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        PoiInfoBean infoBean = dataList.get(position);
        PoiInfo poiInfo = infoBean.info;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.module_biz_activity_map_list_item, parent, false);
            holder.tvTitle = convertView.findViewById(R.id.title);
            holder.tvContent = convertView.findViewById(R.id.content);
            holder.img = convertView.findViewById(R.id.checked);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (infoBean.isChecked) {
            holder.img.setVisibility(View.VISIBLE);
        } else {
            holder.img.setVisibility(View.GONE);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < dataList.size(); i++) {
                    if (position == i) {
                        dataList.get(i).isChecked = true;
                        info = dataList.get(i).info;
                    } else {
                        dataList.get(i).isChecked = false;
                    }
                }
                listener.setLocation(info);
                bindData(dataList);
            }
        });
        holder.tvTitle.setText(poiInfo.name);
        holder.tvContent.setText(poiInfo.address);
        return convertView;
    }

    List<PoiInfoBean> dataList;

    public void bindData(List<PoiInfoBean> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();

    }

    private MyLocationListener listener;

    public void setListener(MyLocationListener listener) {
        this.listener = listener;
    }

    private class ViewHolder {
        TextView tvTitle;
        TextView tvContent;
        ImageView img;
    }
}
