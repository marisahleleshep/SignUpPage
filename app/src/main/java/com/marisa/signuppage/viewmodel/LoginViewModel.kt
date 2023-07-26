package com.marisa.signuppage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.marisa.signuppage.models.LoginRequest
import com.marisa.signuppage.models.LoginResponse
import com.marisa.signuppage.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    val userRepository=LoginRepository()
    val logLiveData=MutableLiveData<LoginResponse>()
    val errorLiveData=MutableLiveData<String>()

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch{
            val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                logLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }

    }

}