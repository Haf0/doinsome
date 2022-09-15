package com.qtn.doinsome.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModelFactory private constructor(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(application) as T
    }

    companion object {

        @Volatile
        private var instance: UserViewModelFactory? = null


        @JvmStatic
        fun getInstance(application: Application):UserViewModelFactory {
            if (instance == null) {
                synchronized(UserViewModelFactory::class.java) {
                        instance = UserViewModelFactory(application)
                }
            }
            return instance as UserViewModelFactory
    }
}
}