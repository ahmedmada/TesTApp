package com.swensonhe.test.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.FtsOptions.Order
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swensonhe.test.model.Item


@Dao
interface ItemDao {

    @Query("SELECT * FROM item")
    fun getAllUsers(): List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItems(order: List<Item>)

    @Delete
    fun deleteUser(item: Item)
}
