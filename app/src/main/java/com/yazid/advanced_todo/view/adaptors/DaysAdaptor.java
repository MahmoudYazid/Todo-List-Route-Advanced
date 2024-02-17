package com.yazid.advanced_todo.view.adaptors;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.yazid.advanced_todo.R;
import com.yazid.advanced_todo.model.DateClass;
import com.yazid.advanced_todo.view.interfaces.IDaysTasks;

import java.util.List;

public class DaysAdaptor extends RecyclerView.Adapter<DaysAdaptor.ViewHolder>{
    public List<DateClass> DaysList;


    View previousSelectedItem = null;
    public IDaysTasks IdaysTasksInst;
    Context context_;
    public  DaysAdaptor (List<DateClass> Inputlist, Context context){
        this.DaysList=Inputlist;
        this.context_=context;

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
        CardView DateCart= holder.itemView.findViewById(R.id.DateCart);
        int currentPosition = holder.getAdapterPosition();

        DateCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Using currentPosition instead of position
                String Taredate = DaysList.get(currentPosition).getDay().toString() + "-" + DaysList.get(currentPosition).getMonth().toString() + "-" + DaysList.get(currentPosition).getYear().toString();
                IdaysTasksInst.GetTaskofDay(Taredate);


                // Change background color of selected item
                view.setBackgroundColor(Color.CYAN);
                if (previousSelectedItem != null && previousSelectedItem != view) {
                    previousSelectedItem.setBackgroundColor(Color.WHITE);
                }
                // Reset background color of previously selected item if any


                // Update previousSelectedItem to current selected item
                previousSelectedItem = view;

            }
        });





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
            CardView DateCart= itemView.findViewById(R.id.DateCart);

        }
    }
}
