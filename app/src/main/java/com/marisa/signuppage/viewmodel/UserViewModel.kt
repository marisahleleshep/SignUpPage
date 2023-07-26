package com.marisa.signuppage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marisa.signuppage.models.RegisterResponse
import com.marisa.signuppage.models.RegisterRequest
import com.marisa.signuppage.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel :ViewModel() {
    val regLiveData=MutableLiveData<RegisterResponse>()
    val errorLiveData=MutableLiveData<String>()
    val userRepository=UserRepository()

    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch{
            val response=userRepository.registerUser(registerRequest)
            if (response.isSuccessful){
                regLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}