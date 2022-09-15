package com.qtn.doinsome.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.qtn.doinsome.data.local.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): MovieDatabase {
            if(INSTANCE == null) {
                synchronized(MovieDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, "movie_db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE as MovieDatabase
        }

    }
}