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
import com.wind.the.snowweather.model.DailyItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by DzungVt on 2/3/2018.
 */

public class DailyViewAdapter extends RecyclerView.Adapter<DailyViewAdapter.ViewHolder> {

    Context context;
    List<DailyItem> dailyItems = new LinkedList<>();

    public DailyViewAdapter(List<DailyItem> dailyItems, Context context) {
        this.dailyItems = dailyItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.daily_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtDate.setText(dailyItems.get(position).getDate());
        holder.txtStatus.setText(dailyItems.get(position).getStatus());
        holder.txtMinTemp.setText(dailyItems.get(position).getMinTemp());
        holder.txtMaxTemp.setText(dailyItems.get(position).getMaxTemp());
        holder.txtDayTemp.setText(dailyItems.get(position).getDayTemp());
        holder.txtMornTemp.setText(dailyItems.get(position).getMornTemp());
        holder.txtAftTemp.setText(dailyItems.get(position).getAftTemp());
        holder.txtEvenTemp.setText(dailyItems.get(position).getEvenTemp());
        holder.lnlItemDaily.setBackgroundResource(dailyItems.get(position).getIntDailyBackgroundURL());
        holder.imgState.setImageResource(dailyItems.get(position).getIntStateURL());
    }

    @Override
    public int getItemCount() {
        return dailyItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtStatus, txtMinTemp, txtMaxTemp, txtDayTemp, txtMornTemp, txtAftTemp, txtEvenTemp;
        LinearLayout lnlItemDaily;
        ImageView imgState;

        public ViewHolder(View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtMinTemp = itemView.findViewById(R.id.txtMinTemp);
            txtMaxTemp = itemView.findViewById(R.id.txtMaxTemp);
            txtDayTemp = itemView.findViewById(R.id.txtDayTemp);
            txtMornTemp = itemView.findViewById(R.id.txtMornTemp);
            txtAftTemp = itemView.findViewById(R.id.txtAftTemp);
            txtEvenTemp = itemView.findViewById(R.id.txtEvenTemp);
            lnlItemDaily = itemView.findViewById(R.id.lnlItemDaily);
            imgState = itemView.findViewById(R.id.imgState);

        }
    }
}
