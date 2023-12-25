package com.swensonhe.test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swensonhe.test.model.Item
import com.swensonhe.test.repository.MainRepository
import com.swensonhe.test.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _items = MutableLiveData<Resource<List<Item>>>()

    val items: LiveData<Resource<List<Item>>>
        get() = _items

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch(Dispatchers.IO) {
        _items.postValue(Resource.loading(null))
        mainRepository.getData().let {
            if (it.isSuccessful) {
                _items.postValue(Resource.success(it.body()?.data))
                insertAllItem(it.body()?.data)
            } else {
                _items.postValue(Resource.success(getAllItems()))
//                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }


    private fun getAllItems(): List<Item> {
        return mainRepository.getAllItem()
    }

    fun insertItem(item: Item) {
        mainRepository.insertItem(item)
    }

    private fun insertAllItem(item: List<Item>?) {
        if (item.isNullOrEmpty()) return
        mainRepository.insertAllItem(item)
    }

    fun deleteItem(item: Item) {
        mainRepository.deleteItem(item)
    }


}