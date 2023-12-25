package com.swensonhe.test.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.swensonhe.test.model.Item

@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

}
