package com.qtn.doinsome.data.local

import android.app.Application
import com.qtn.doinsome.data.local.database.MovieDao
import com.qtn.doinsome.data.local.database.MovieDatabase
import com.qtn.doinsome.data.local.entity.UserEntity
import java.util.concurrent.Executors

class LocalRepository(application: Application) {
    private val dao:MovieDao
    private val executorService = Executors.newSingleThreadExecutor()

    init {
        val db = MovieDatabase.getDatabase(application)
        dao = db.movieDao()
    }

    fun getDataUser(email: String, password: String): Boolean {
        val user = dao.readDataUser(email, password)
        return user.email == email && user.password == password
    }
    fun registerUser(user: UserEntity){
        executorService.execute { dao.registerUser(user) }
    }


}