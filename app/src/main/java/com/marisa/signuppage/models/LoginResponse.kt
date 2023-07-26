package com.marisa.signuppage.models

data class LoginResponse(
    var message:String,
    var access_token:String,
    var userId:String
    )
