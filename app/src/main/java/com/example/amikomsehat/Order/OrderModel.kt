package com.example.amikomsehat

import androidx.lifecycle.ViewModel

class OrderModel {
    companion object {
        private val orderList: MutableList<Order> = mutableListOf()

        fun addDataOrder(title: String?, totalItems: Int, totalPrice: Int) {
            val order = Order(title, totalItems, totalPrice)
            orderList.add(order)
        }

        fun getOrderList(): List<Order> {
            return orderList
        }
    }
}

data class Order(val title: String?, val totalItems: Int, val totalPrice: Int)
