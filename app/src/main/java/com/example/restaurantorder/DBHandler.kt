package com.example.restaurantorder

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler (context: Context, factory: SQLiteDatabase.CursorFactory?, version: Int):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "ordersDB.db"
        val TABLE_ORDERS = "orders_table"
        val COLUMN_ID = "_id"
        val COLUMN_DISH = "dish"
        val COLUMN_CLIENT = "client"
    }

    override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("CREATE TABLE $TABLE_ORDERS ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_DISH TEXT, $COLUMN_CLIENT TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ORDERS")
        onCreate(db)
    }

    fun addOrder(order: Order) {
        val values = ContentValues()
        values.put(COLUMN_DISH, order.dish)
        values.put(COLUMN_CLIENT, order.client)
        val db = this.writableDatabase
        db.insert(TABLE_ORDERS, null, values)
        db.close()
    }

    fun getOrders(): MutableList<Order> {
        val db = this.writableDatabase
        val orders:  MutableList<Order> = mutableListOf()
        val cursor = db.rawQuery("SELECT * FROM $TABLE_ORDERS", null)
        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast) {
                orders.add(Order(cursor.getString(1), cursor.getString(2)))
                cursor.moveToNext()
            }
        }
        db.close()
        return orders
    }

}