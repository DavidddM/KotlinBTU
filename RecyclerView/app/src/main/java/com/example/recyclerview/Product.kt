package com.example.recyclerview

import androidx.annotation.DrawableRes

data class Product(
    val title: String,
    val price: String,
    @DrawableRes
    val image: Int
) {
    var id: Int = 0

    companion object {
        var sId = 1
    }

    init {
        this.id = Product.sId++
    }
}