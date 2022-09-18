package com.qtn.doinsome.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idUser")
    var idUser: Int = 0,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "bornDate")
    var bornDate: String
    ) : Parcelable


