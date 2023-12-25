package com.swensonhe.test.model

data class Data(
    var msg: String,
    var status: Int,
    var data: ArrayList<Item> = arrayListOf()
)
