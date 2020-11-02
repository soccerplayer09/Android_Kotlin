package com.example.nav_1023

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserInfoDao {

    @Query("SELECT * FROM UserInfo")
    fun getAll(): LiveData<List<UserInfo>>
    //이제 메인에서 옵저브로 바꿔주면 댐

    @Query("SELECT * FROM UserInfo WHERE Info_Id LIKE :search")
    fun findUserWithID(search: String): LiveData<UserInfo>
    //없으면 null값 리턴됨
    @Insert
    fun insert(userinfo: UserInfo)
    @Delete
    fun delete(userinfo: UserInfo)

}