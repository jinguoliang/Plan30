package com.example.jinux.thirtydays.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jinux.thirtydays.R;
import com.example.jinux.thirtydays.activity.PlanDetailActivity;
import com.example.jinux.thirtydays.bean.PlanItem;
import com.example.jinux.thirtydays.common.Controller;

import java.util.List;

/**
 * Created by jinux on 15-4-6.
 */
public class PlanListAdapter extends BaseAdapter{

    private final Context mContext;
    private List<PlanItem> mData;

    public PlanListAdapter(Context c, List<PlanItem> data){
        this.mContext =c;
        this.mData = data;
    }
    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlanItem planItem = mData.get(position);
        ViewHolder holder = new ViewHolder();

        if (convertView == null){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_plan,null);
            holder.tvPlanName = (TextView) itemView.findViewById(R.id.tvItemPlanName);
            itemView.setTag(holder);
            convertView = itemView;
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvPlanName.setText(planItem.getName());


        return convertView;
    }

    public void setData(List<PlanItem> data){
        mData = data;
        notifyDataSetChanged();
    }

    public void addData(List<PlanItem> data){
        mData.addAll(data);
        notifyDataSetChanged();
    }


    class ViewHolder {
        TextView  tvPlanName;
    }
}
