package com.yazid.advanced_todo.view.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.yazid.advanced_todo.R;
import com.yazid.advanced_todo.model.Tasks_Info_Class;

import java.util.List;

public class TasksAdaptor extends RecyclerView.Adapter<TasksAdaptor.Holder> {
    List<Tasks_Info_Class> Internal_List;
    public void SetData(List<Tasks_Info_Class> newData){
        this.Internal_List=newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.activity_task_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        TextView titleTask = holder.itemView.findViewById(R.id.title);
        TextView dateTask = holder.itemView.findViewById(R.id.date);
        Tasks_Info_Class item = Internal_List.get(position);
        titleTask.setText(item.getTask());
        dateTask.setText(item.getDate());

    }

    @Override
    public int getItemCount() {
        if (Internal_List != null) {
            return Internal_List.size();
        } else {
            return 0;
        }
    }

    class Holder extends RecyclerView.ViewHolder {

        public Holder(@NonNull View itemView) {
            super(itemView);
            TextView titleTask = itemView.findViewById(R.id.title);
            TextView dateTask = itemView.findViewById(R.id.date);


        }
    }
}
