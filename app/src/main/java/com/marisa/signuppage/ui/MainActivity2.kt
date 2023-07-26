package com.marisa.signuppage.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.marisa.signuppage.databinding.ActivityMain2Binding
import com.marisa.signuppage.models.LoginRequest
import com.marisa.signuppage.models.LoginResponse
import com.marisa.signuppage.models.RegisterRequest
import com.marisa.signuppage.viewmodel.LoginViewModel

class MainActivity2 : AppCompatActivity() {
    val loginViewModel:LoginViewModel by viewModels()
     lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()

        binding.btnLogin.setOnClickListener {
            validateSignup()
            clearErrors()
        }
        loginViewModel.errorLiveData.observe(this, Observer { err->
            Toast.makeText(this,err,Toast.LENGTH_LONG).show()
            binding.pbLoad.visibility = View.GONE
        })
        loginViewModel.logLiveData.observe(this, Observer { logResponse->
            binding.pbLoad.visibility=View.GONE
            Toast.makeText(this,logResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity3::class.java))
        })

    }

     fun validateSignup(){
        val email = binding.etEmails.text.toString()
        val password = binding.etPasswords.text.toString()
        var error = false


        if(email.isBlank()){
            binding.tilEmails.error = "Enter your email"
            error = true
        }

        if(password.isBlank()){
            binding.tilPasswords.error = "enter your password"
            error = true
        }
         if (!error){
             val loginRequest = LoginRequest(
                 email = email,
                 password = password
             )
             binding.pbLoad.visibility = View.VISIBLE
             loginViewModel.loginUser(loginRequest)
         }
    }

    private fun clearErrors(){
        binding.tilEmails.error =null
        binding.tilPasswords.error= null
    }
}