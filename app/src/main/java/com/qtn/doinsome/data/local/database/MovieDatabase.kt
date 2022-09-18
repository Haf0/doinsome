package com.qtn.doinsome.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.qtn.doinsome.data.local.entity.MovieEntity
import com.qtn.doinsome.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class,MovieEntity::class],
    version = 2,
    exportSchema = false,
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao


    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        val MIGRATION_1_2:Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }

        @JvmStatic
        fun getDatabase(context: Context): MovieDatabase {
            if(INSTANCE == null) {
                synchronized(MovieDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, "movie_db")
                        .allowMainThreadQueries()
                        .addMigrations(MIGRATION_1_2)
                        .build()
                }
            }
            return INSTANCE as MovieDatabase
        }


    }
}