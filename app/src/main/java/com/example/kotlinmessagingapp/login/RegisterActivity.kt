package com.example.kotlinmessagingapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.kotlinmessagingapp.R
import com.example.kotlinmessagingapp.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    //input boxes
    private lateinit var emailTextBox: EditText
    private lateinit var usernameTextBox: EditText
    private lateinit var passwordTextBox: EditText

    //buttons
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button


    //authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        emailTextBox = findViewById(R.id.registerEmail)
        usernameTextBox = findViewById(R.id.registerUsername)
        passwordTextBox = findViewById(R.id.registerPassword)

        registerButton = findViewById(R.id.registerSendButton)
        loginButton = findViewById(R.id.toSigninButton)

        auth = Firebase.auth
    }

    fun register(v: View) {
        auth.createUserWithEmailAndPassword(
            emailTextBox.text.toString(),
            passwordTextBox.text.toString()
        ).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                //log
                Log.d("EmailPasswordCreation", "username passwordSuccess")

                //get user
                val user = auth.currentUser

                //add username
                updateProfile(user)

                //goto main activity
                toMain()
            }
        }
    }

    private fun updateProfile(user: FirebaseUser?) {
        //profile request builder
        val profileUpdates = userProfileChangeRequest {
            displayName = usernameTextBox.text.toString()
        }

        //add username to user
        user!!.updateProfile(profileUpdates).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("EmailPasswordCreation", "username success")
            }
        }
    }

    fun toLogin(v: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun toMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}