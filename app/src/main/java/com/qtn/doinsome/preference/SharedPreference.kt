package com.qtn.doinsome.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class SharedPreference @Inject constructor(private val dataStore: DataStore<Preferences>) {

   fun getState(): Flow<SessionModel>{
       return dataStore.data.map {
           SessionModel(
               it[LOGGED_EMAIL]?: "",
               it[STATE]?: false
           )
       }
   }

    suspend fun saveState(model: SessionModel){
        dataStore.edit {
            it[LOGGED_EMAIL] = model.email
            it[STATE] = model.state
        }
    }

    suspend fun setLoginState(){
        dataStore.edit {
            it[STATE] = true
        }
    }
    suspend fun logout(){
        dataStore.edit {
            it.clear()
        }
    }

    companion object{
        private val STATE = booleanPreferencesKey("state")
        private val LOGGED_EMAIL = stringPreferencesKey("email")
    }

}