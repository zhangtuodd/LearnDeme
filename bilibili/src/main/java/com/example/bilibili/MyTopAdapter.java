package com.example.bilibili;

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

public class MyTopAdapter extends BaseAdapter {
    private BaiduMapActivity context;
    private PoiInfo info;

    public MyTopAdapter(BaiduMapActivity context) {
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
        final PoiInfo poiInfo = infoBean.info;
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
        holder.img.setVisibility(View.GONE);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏top块
                context.headView.setVisibility(View.VISIBLE);
                context.llSearchTwo.setVisibility(View.GONE);
                //将选择条目的标题填入EditText
                context.tvSearchOne.setText(poiInfo.name);
                //定位
                listener.setLocation(poiInfo);
                //搜索
                //将搜索结果传递给below List
                listener.byTopItemRefreshBelow(poiInfo);
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
