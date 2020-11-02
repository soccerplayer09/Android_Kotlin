package com.example.nav_1023

import android.app.Application
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "database-name"
    ).build()

    var user: UserInfo = UserInfo()

    fun findUserWithID(search: String): LiveData<UserInfo> {
        return db.userInfoDao().findUserWithID(search)
    }
    fun insert() {
        viewModelScope.launch(Dispatchers.IO) {
            db.userInfoDao().insert(user)
        }
    }
}

