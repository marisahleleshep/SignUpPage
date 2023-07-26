package com.marisa.signuppage.repository

import com.marisa.signuppage.api.ApiClient
import com.marisa.signuppage.api.ApiInterface
import com.marisa.signuppage.models.LoginRequest
import com.marisa.signuppage.models.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository {
    var apiClients=ApiClient.buildClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest):Response<LoginResponse>{
        return withContext(Dispatchers.IO){
            apiClients.loginUsers((loginRequest))
        }
    }
}