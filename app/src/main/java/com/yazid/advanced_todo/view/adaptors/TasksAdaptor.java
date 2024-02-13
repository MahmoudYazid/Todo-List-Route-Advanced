package com.yazid.advanced_todo.view.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yazid.advanced_todo.R;
import com.yazid.advanced_todo.model.Tasks_Info_Class;

import java.util.List;

public class TasksAdaptor extends RecyclerView.Adapter<TasksAdaptor.Holder> {
    List<Tasks_Info_Class> Internal_List;
    public TasksAdaptor(List<Tasks_Info_Class> list){

        this.Internal_List=list;
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

    }

    @Override
    public int getItemCount() {
        return Internal_List.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
