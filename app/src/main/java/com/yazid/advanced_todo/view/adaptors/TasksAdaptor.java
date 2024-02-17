package com.yazid.advanced_todo.view.adaptors;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.yazid.advanced_todo.R;
import com.yazid.advanced_todo.model.Tasks_Info_Class;
import com.yazid.advanced_todo.view.interfaces.ITasks_adaptor_Functions;

import java.util.List;

public class TasksAdaptor extends RecyclerView.Adapter<TasksAdaptor.Holder> {
    List<Tasks_Info_Class> Internal_List;
    public ITasks_adaptor_Functions InterfaceFunctions;
    Context context;


    public void SetData(List<Tasks_Info_Class> newData){
        this.Internal_List=newData;
        notifyDataSetChanged();

    }
    public TasksAdaptor(Context context){
        this.context=context;
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
        ImageView DeleteTask = holder.itemView.findViewById(R.id.Delete_btm_Item);
        ImageView modifyTask = holder.itemView.findViewById(R.id.modify_btm_item);
        ImageButton DoneBtm = holder.itemView.findViewById(R.id.doneBtm);

        // go to modification
        modifyTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterfaceFunctions.Go_T_modify_Item_in_Adaptor(item);
            }
        });
        // Delete
        DeleteTask.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                InterfaceFunctions.Delete_Item_in_Adaptor(item);
            }
        });
        // Done
        DoneBtm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();

                    Tasks_Info_Class clickedItem = Internal_List.get(adapterPosition);
                    if (clickedItem.getDone_state().equals("no")) {
                        InterfaceFunctions.SetTaskState_Adaptor(item,"done");
                        notifyDataSetChanged();

                        Log.e("fuck",item.getDone_state().toString());
                    }

                if (clickedItem.getDone_state().equals("done"))
                    {
                        InterfaceFunctions.SetTaskState_Adaptor(item,"no");
                        notifyDataSetChanged();
                        Log.e("fuck",item.getDone_state().toString());

                    }

            }
        });

        // Check done state
        if (item.getDone_state().equals("done")){
            titleTask.setTextColor(Color.GREEN);
            DoneBtm.setBackgroundResource(R.drawable.btm_task_item_shape_click);
        }else {

            titleTask.setTextColor(Color.BLACK);
            DoneBtm.setBackgroundResource(R.drawable.btm_task_item_shape);


        }

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
            ImageView DeleteTask = itemView.findViewById(R.id.Delete_btm_Item);
            ImageView modifyTask = itemView.findViewById(R.id.modify_btm_item);
            ImageButton DoneBtm = itemView.findViewById(R.id.doneBtm);


        }
    }
}
