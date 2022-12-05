package com.example.fit.data.component.Login

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fit.data.component.User.user_infor


data class user_account_role(
    @Embedded val userRole: user_role,
    @Relation(
        parentColumn = "RoleId",
        entityColumn = "RoleId",
        )
    val UserInfor: user_infor,
){

}