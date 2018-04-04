package com.wind.the.snowweather.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wind.the.snowweather.R;
import com.wind.the.snowweather.model.HoursItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by DzungVt on 2/27/2018.
 */

public class HoursViewAdapter extends RecyclerView.Adapter<HoursViewAdapter.ViewHolder> {
    Context context;
    List<HoursItem> hoursItems = new LinkedList<>();

    public HoursViewAdapter(Context context, List<HoursItem> hoursItems) {
        this.context = context;
        this.hoursItems = hoursItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.hours_column, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtHours.setText(hoursItems.get(position).getHours());
        holder.txtHoursTemp.setText(hoursItems.get(position).getHoursTemp());
        holder.imgState.setImageResource(hoursItems.get(position).getIntURL());
        holder.lnlItemHours.setBackgroundResource(hoursItems.get(position).getTimeOfDay());
    }

    @Override
    public int getItemCount() {
        return hoursItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHours, txtHoursTemp;
        ImageView imgState;
        LinearLayout lnlItemHours;

        public ViewHolder(View itemView) {
            super(itemView);
            txtHours = itemView.findViewById(R.id.txtHours);
            txtHoursTemp = itemView.findViewById(R.id.txtHoursTemp);
            imgState = itemView.findViewById(R.id.imgState);
            lnlItemHours = itemView.findViewById(R.id.lnlItemHours);
        }
    }
}
