package com.qtn.doinsome.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.qtn.doinsome.data.local.LocalRepository
import com.qtn.doinsome.data.local.model.UserEntity

class UserViewModel(application: Application):ViewModel() {
    private val repository = LocalRepository(application)

    fun registerUser(user:UserEntity) = repository.registerUser(user)
}