package com.example.fit.data.component.Login

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity
data class user_infor(
    @PrimaryKey()
    var UserId:Int,
    var FirstName:String,
    var LastName:String,
    var Gender:String,
    var DateOfBirth: Date,
    var RoleId:Int
    ): Serializable {
}