package com.example.nav_1023

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserInfo::class], version = 1 , exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
}