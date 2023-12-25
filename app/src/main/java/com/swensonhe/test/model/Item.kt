package com.swensonhe.test.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    val value: String,
    @PrimaryKey val id: String,
    val type: Int = 0
)
