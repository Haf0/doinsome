package com.qtn.doinsome.viewmodel

import androidx.lifecycle.ViewModel
import com.qtn.doinsome.data.local.LocalRepository
import com.qtn.doinsome.data.local.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: LocalRepository) :ViewModel() {

    fun registerUser(user:UserEntity) = repository.registerUser(user)

}