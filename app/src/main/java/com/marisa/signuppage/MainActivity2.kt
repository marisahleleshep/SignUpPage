package com.marisa.signuppage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marisa.signuppage.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        validateSignup()
        clearErrors()
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


    }

    fun clearErrors(){
        binding.tilEmails.error =null
        binding.tilPasswords.error= null
    }
}