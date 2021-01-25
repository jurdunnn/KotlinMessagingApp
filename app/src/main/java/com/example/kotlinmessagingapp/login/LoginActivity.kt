package com.example.kotlinmessagingapp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
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
        val password: String = passwordTextBox.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    toMain()
                } else {
                    // If sign in fails, display a message to the user.
                }
            }
    }

    public fun toRegister(v: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun toMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}