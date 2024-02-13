package com.yazid.advanced_todo.view.swipe_config

import android.R
import android.content.Context
import android.graphics.Canvas
import android.util.TypedValue
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.yazid.advanced_todo.view.adaptors.TasksAdaptor
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class swipeToDelete(private val context: Context) : ItemTouchHelper.Callback() {


    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val maxSwipeDistance= 1
        var constrainedDX = dX
        if (constrainedDX > maxSwipeDistance) {
            constrainedDX = maxSwipeDistance.toFloat()
        } else if (constrainedDX < -maxSwipeDistance) {
            constrainedDX = -maxSwipeDistance.toFloat()
        }
        RecyclerViewSwipeDecorator.Builder(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
            .addSwipeRightBackgroundColor(ContextCompat.getColor(context, R.color.holo_red_light))
            .addSwipeRightActionIcon(com.yazid.advanced_todo.R.drawable.baseline_restore_from_trash_24)
            .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, R.color.holo_green_light))
            .addSwipeLeftActionIcon(com.yazid.advanced_todo.R.drawable.baseline_settings_24)



            .addCornerRadius(TypedValue.COMPLEX_UNIT_DIP,30)
            .create()
            .decorate()
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val recyclerView = viewHolder.itemView.parent as RecyclerView
        val adapter = recyclerView.adapter as TasksAdaptor



    }

}