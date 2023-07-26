package com.marisa.signuppage.repository

import com.marisa.signuppage.api.ApiClient
import com.marisa.signuppage.api.ApiInterface
import com.marisa.signuppage.models.RegisterRequest
import com.marisa.signuppage.models.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

//import okhttp3.Dispatcher
//import okhttp3.Response


class UserRepository {
    var client=ApiClient.buildClient(ApiInterface::class.java)

    suspend fun registerUser(RegisterRequest:RegisterRequest):
            Response<RegisterResponse> {
        return withContext(Dispatchers.IO){
            client.registerUser(RegisterRequest)
        }
    }
}