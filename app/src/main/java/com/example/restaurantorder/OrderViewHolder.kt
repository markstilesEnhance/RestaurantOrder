package com.example.restaurantorder

import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(order: Order, clickListener: (Order) -> Unit) {
        itemView.order_text.text = order.dish + " ordered by: " + order.client
        itemView.setOnClickListener{clickListener(order)}
    }
}