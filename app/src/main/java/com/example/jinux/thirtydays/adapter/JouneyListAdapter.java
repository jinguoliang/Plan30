package com.example.jinux.thirtydays.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jinux.thirtydays.R;
import com.example.jinux.thirtydays.activity.PlanDetailActivity;
import com.example.jinux.thirtydays.bean.JourneyItem;
import com.example.jinux.thirtydays.bean.PlanItem;
import com.example.jinux.thirtydays.common.Controller;

import java.util.List;

/**
 * Created by jinux on 15-4-6.
 */
public class JouneyListAdapter extends BaseAdapter{

    private final Context mContext;
    private final List<JourneyItem> mData;

    public JouneyListAdapter(Context c, List<JourneyItem> data){
        this.mContext =c;
        this.mData = data;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JourneyItem planItem = mData.get(position);
        ViewHolder holder = new ViewHolder();

        if (convertView == null){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_journey,null);
            holder.tvDay = (TextView) itemView.findViewById(R.id.tvItemDay);
            holder.tvContent = (TextView) itemView.findViewById(R.id.tvItemContent);
            itemView.setTag(holder);
            convertView = itemView;
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvDay.setText(planItem.day);
        holder.tvContent.setText(planItem.content);

        return convertView;
    }

    class ViewHolder {
        TextView  tvDay;
        TextView tvContent;
    }
}
