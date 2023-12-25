package com.swensonhe.test.repository

import com.swensonhe.test.api.ApiHelper
import com.swensonhe.test.local.ItemDao
import com.swensonhe.test.model.Item
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val itemDao: ItemDao
) {
    suspend fun getData() = apiHelper.getData()

    fun getAllItem() = itemDao.getAllUsers()

    fun insertItem(item: Item) = itemDao.insertUser(item)

    fun insertAllItem(items: List<Item>) = itemDao.insertAllItems(items)

    fun deleteItem(item: Item) = itemDao.deleteUser(item)
}
