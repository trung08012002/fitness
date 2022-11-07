package com.example.fit.data.component.Login

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "userlogin")
data class user_login(
    @PrimaryKey(autoGenerate = true)
    var UserId: Int?=null,
    var LoginName: String,
    var Password: String,
    var EmailAdress: String?=null,
    var ConfirmationToken: String?=null,
    var TokenGenerationTime: Long=Date().time,
    var EmailValidationStatusId: Int=0,
    var PasswordRecoveryToken:String?=null,
    var RecoveryTokenTime:Long=Date().time+1000*60*12
) : Serializable

