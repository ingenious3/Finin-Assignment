package com.example.fininassignment.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.fininassignment.data.DataManager
import com.example.fininassignment.data.DataSourceFactory
import com.example.fininassignment.data.model.User

class UserViewModel : ViewModel() {
    var userPagedList: LiveData<PagedList<User>>
    private var liveDataManager: LiveData<DataManager>
    init {
        val itemDataSourceFactory = DataSourceFactory()
        liveDataManager = itemDataSourceFactory.dataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(DataManager.PAGE_OFFSET)
            .build()
        userPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }
}