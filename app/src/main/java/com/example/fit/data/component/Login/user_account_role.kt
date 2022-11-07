package com.example.fit.data.component.Login

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


data class user_account_role(
    @Embedded val userRole: user_role,
    @Relation(
        parentColumn = "RoleId",
        entityColumn = "RoleId",
        )
    val UserInfor: user_infor,
){

}