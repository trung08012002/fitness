package com.example.fit.data.component.Login

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class email_validation_status(
    @PrimaryKey(autoGenerate = true)
    var EmailValidationStatusId:Int?=null,
    var StatusDescription:String
): Serializable {
}