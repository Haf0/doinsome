package com.qtn.doinsome.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.qtn.doinsome.preference.SessionModel
import com.qtn.doinsome.preference.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatastoreViewModel @Inject constructor(private val preference: SharedPreference):ViewModel(){

    fun saveUser(user: SessionModel) {
        viewModelScope.launch {
            preference.saveState(user)
        }
    }

    fun login(){
        viewModelScope.launch {
            preference.setLoginState()
        }
    }
    fun logout(){
        viewModelScope.launch {
            preference.logout()
        }
    }

    fun getLoginState(): LiveData<SessionModel> = preference.getState().asLiveData()
}