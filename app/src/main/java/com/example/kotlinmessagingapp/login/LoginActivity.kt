package com.example.kotlinmessagingapp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinmessagingapp.R
import com.example.kotlinmessagingapp.main.MainActivity
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    //input boxes
    private lateinit var emailTextBox: EditText
    private lateinit var passwordTextBox: EditText

    //buttons
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    //authentication
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //get user input boxes
        emailTextBox = findViewById(R.id.emailTextBox)
        passwordTextBox = findViewById(R.id.passwordTextBox)

        //get buttons
        loginButton = findViewById(R.id.signinButton)
        registerButton = findViewById(R.id.registerButton)

        //get instance of authentication
        auth = FirebaseAuth.getInstance()

    }

    fun login(v: View) {
        val email: String = emailTextBox.text.toString()
        val password: String = passwordTextBox.text.toString()

        if(runChecks(email, password)) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser

                        //toast sign in success
                        Toast.makeText(this, "Signed in", Toast.LENGTH_SHORT).show()

                        //go to main activityy
                        toMain()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "Failed to retrieve account", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Could not recognise email and/or password", Toast.LENGTH_SHORT).show()
        }

    }

    fun toRegister(v: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun toMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun runChecks(email: String, password: String): Boolean {
        return (email.isNotEmpty() //Check email field is not empty
                && email.contains("@") // Check email field contains an @ symbol
                && password.isNotEmpty()) // Check password is not empty
    }


}