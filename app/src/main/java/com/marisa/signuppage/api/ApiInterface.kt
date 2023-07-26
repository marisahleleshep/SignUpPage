package com.marisa.signuppage.api

import com.marisa.signuppage.models.LoginRequest
import com.marisa.signuppage.models.LoginResponse
import com.marisa.signuppage.models.RegisterResponse
import com.marisa.signuppage.models.RegisterRequest
//import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

interface ApiInterface {
    @POST("/users/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>

    @POST("/users/login")
    suspend fun loginUsers(@Body loginRequest: LoginRequest):Response<LoginResponse>
}




