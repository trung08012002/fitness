package com.example.fit.data.component.Login

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class user_role(
    @PrimaryKey(autoGenerate = true)
    var RoleId:Int?=null,
    var RoleDescription:String
)