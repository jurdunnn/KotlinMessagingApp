package com.example.kotlinmessagingapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.kotlinmessagingapp.R
import com.example.kotlinmessagingapp.main.MainActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
        //run format checks
        if(runChecks(emailTextBox.text.toString(), passwordTextBox.text.toString(), usernameTextBox.text.toString())) {
            auth.createUserWithEmailAndPassword(
                emailTextBox.text.toString(),
                passwordTextBox.text.toString()
            ).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //log
                    Log.d("EmailPasswordCreation", "email password success")

                    //get user
                    val user = auth.currentUser

                    //add username
                    updateProfile(user)

                    //toast the user
                    Toast.makeText(this, "Welcome! Please enjoy!", Toast.LENGTH_SHORT).show()

                    //goto main activity
                    toMain()
                }
            }.addOnFailureListener(OnFailureListener {
                Log.d("EmailPasswordCreation", "email password failure")
                Toast.makeText(this, "Something went wrong...", Toast.LENGTH_SHORT).show()
            })
        } else {
            Toast.makeText(this, "Error: Please ensure formatting is correct.", Toast.LENGTH_SHORT).show() //Todo less vague
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
        }.addOnFailureListener(OnFailureListener {
            Log.d("EmailPasswordCreation", "username failure")
        })
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

    //if back button is pressed open login activity
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun runChecks(email: String, password: String, username: String): Boolean {
        return (email.isNotEmpty() //check email is not empty
                && email.contains('@') //check email format
                && password.isNotEmpty() // check password is not empty
                && password.length > 5 // check password length
                && username.isNotEmpty() // check username is not empty
                && !username.contains(' ')) //check username does not contain a space
    }
}