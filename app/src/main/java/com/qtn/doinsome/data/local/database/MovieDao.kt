package com.qtn.doinsome.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qtn.doinsome.data.local.entity.MovieEntity
import com.qtn.doinsome.data.local.entity.UserEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerUser(user: UserEntity)

    @Query("SELECT * FROM user WHERE email LIKE :email AND password LIKE :password")
    fun readDataUser(email: String, password: String): UserEntity

    @Query("SELECT email FROM user WHERE email=:email")
    fun validateEmail(email: String) : String

    @Query("SELECT username FROM user WHERE username=:username")
    fun validateUsername(username: String) : String

    @Query("SELECT * FROM movie")
    fun readDataMovie(): List<MovieEntity>

}