package com.example.restaurantorder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView

class OrderListAdapter(private var list: MutableList<Order>, private val clickListener: (Order) -> Unit)
    : RecyclerView.Adapter<OrderViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
       holder.bind(list[position], clickListener)
    }

    fun updateList(order: MutableList<Order>) {
        list.clear()
        list = order
        notifyDataSetChanged()
    }
}