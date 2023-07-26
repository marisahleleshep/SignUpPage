package com.marisa.signuppage.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.marisa.signuppage.databinding.ActivityMainBinding
import com.marisa.signuppage.models.RegisterRequest
import com.marisa.signuppage.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    val userViewModel:UserViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onResume() {
        super.onResume()

        binding.btnSignup.setOnClickListener{
            clearErrors()
            validateSignUp()
        }
        binding.tvLogin.setOnClickListener {
            val intent=Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        userViewModel.regLiveData.observe(this, Observer { regResponse ->
            Toast.makeText(this,regResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity2::class.java))
            binding.pdRegistration.visibility=View.GONE
        })
        userViewModel.errorLiveData.observe(this, Observer { err ->
            Toast.makeText(this,err,Toast.LENGTH_LONG).show()
            binding.pdRegistration.visibility=View.GONE
        })
//        binding.btnSignup.setOnClickListener {
//            startActivity(Intent(this,MainActivity2::class.java))
//        }
    }

    fun validateSignUp() {
        val firstName=binding.etfirstName.text.toString()
        val lastName=binding.etLastName.text.toString()
        val phoneNumber=binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etpassword.text.toString()
        val confirm =binding.etConPassword.text.toString()
        var error = false

        if (firstName.isBlank()) {
            error = true
            binding.tilFirstName.error = "First name is required"
        }
        if (lastName.isBlank()) {
            binding.tilFirstName.error = "Last name is required"
            error = true
        }
        if (email.isBlank()) {
            binding.tilEmail.error = "Email is required"
            error = true
        }
        if (password.isBlank()) {
            binding.tilpassword.error = "Password is required"
            error = true
        }
        if (confirm != password){
            binding.tilConPassword.error = "password does not match"
            error=true
        }
        if (phoneNumber.isBlank()) {
            binding.tilphoneNumber.error = "Your phone number is required"
            error = true
        }
        if(!error){
            val registerRequest=RegisterRequest(
                firstName =firstName,
                lastName = lastName,
                email=email,
                phoneNumber  = phoneNumber,
                password = password
            )
            userViewModel.registerUser(registerRequest)
            binding.pdRegistration.visibility = View.VISIBLE
        }
    }

    fun clearErrors() {
        binding.tilFirstName.error = null
        binding.tilLastName
        binding.tilEmail.error = null
        binding.tilpassword.error = null
        binding.tilphoneNumber.error = null
        binding.tilConPassword.error=null
    }
}