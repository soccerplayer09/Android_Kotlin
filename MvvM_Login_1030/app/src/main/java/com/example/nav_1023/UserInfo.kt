package com.example.nav_1023

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(
    var Info_Id: String = "",
    var Password: String = "",
    var Info_name: String =""
    /*
    val firstName: String = "",
    val lastName: String = ""
     */
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}