package com.example.fininassignment.data

import com.example.fininassignment.data.model.User
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class DataSourceFactory : DataSource.Factory<Int, User>() {

    val dataSource = MutableLiveData<DataManager>()

    override fun create(): DataSource<Int, User> {
        val dataManager = DataManager()
        dataSource.postValue(dataManager)
        return dataManager
    }
}