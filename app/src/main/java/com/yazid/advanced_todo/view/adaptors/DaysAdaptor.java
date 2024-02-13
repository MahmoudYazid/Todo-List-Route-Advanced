package com.yazid.advanced_todo.view.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yazid.advanced_todo.R;
import com.yazid.advanced_todo.model.DateClass;

import java.util.List;

public class DaysAdaptor extends RecyclerView.Adapter<DaysAdaptor.ViewHolder>{
    public List<DateClass> DaysList;
    public  DaysAdaptor ( List<DateClass> Inputlist){
        this.DaysList=Inputlist;


    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View NewView =
                LayoutInflater
                .from(parent.getContext())
                        .inflate(R.layout.activity_date_card_item,parent,false);
        return new ViewHolder(NewView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView textDay = holder.itemView.findViewById(R.id.DayText);
        TextView textMonth = holder.itemView.findViewById(R.id.MonthText);
        TextView textyear = holder.itemView.findViewById(R.id.yearText);
        textDay.setText(DaysList.get(position).getDay());
        textMonth.setText(DaysList.get(position).getMonth());
        textyear.setText(DaysList.get(position).getYear());
    }

    @Override
    public int getItemCount() {
        return DaysList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            TextView TextView_Day= itemView.findViewById(R.id.DayText);
            TextView TextView_month= itemView.findViewById(R.id.MonthText);
            TextView TextView_Year= itemView.findViewById(R.id.yearText);

        }
    }
}
