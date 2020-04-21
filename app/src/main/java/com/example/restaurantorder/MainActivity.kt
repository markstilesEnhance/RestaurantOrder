package com.example.restaurantorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var databaseHelper: DBHandler
    lateinit var adapter: OrderListAdapter
    private var orderList: MutableList<Order> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseHelper = DBHandler(this, null, 1)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        order_list.layoutManager = LinearLayoutManager(this)
        val initialData : MutableList<Order> = mutableListOf()
        adapter = OrderListAdapter(initialData) {order: Order -> partItemClicked(order)}
        order_list.adapter=adapter
    }

    private fun partItemClicked(order: Order) {
        Toast.makeText(this, "Order Selected", Toast.LENGTH_LONG).show()
    }

    fun submit(view: View) {
        databaseHelper.addOrder(Order(order_input.text.toString(), name_input.text.toString()))
        order_input.setText("")
        name_input.setText("")
        Toast.makeText(this@MainActivity, "Order Submitted!", Toast.LENGTH_SHORT).show()
        orderList = databaseHelper.getOrders()
        adapter.updateList(orderList)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        orderList = databaseHelper.getOrders()
        adapter.updateList(orderList)
    }
}
