package com.example.fit.data.component.Login

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import java.io.Serializable


data class UserAndEmail(
    @Embedded val emailValidationStatus: email_validation_status,
    @Relation(
        parentColumn ="EmailValidationStatusId",
        entityColumn = "EmailValidationStatusId"
    )
    val userLogin: user_login
):Serializable