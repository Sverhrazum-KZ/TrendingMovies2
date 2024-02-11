package com.example.popularmoviesattempt2.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.popularmoviesattempt2.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {

    lateinit var editTextEmail: TextInputEditText
    lateinit var editTextPassword: TextInputEditText
    lateinit var buttonReg: Button
    lateinit var auth: FirebaseAuth
    lateinit var progressBar: ProgressBar
    lateinit var textView: TextView

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        buttonReg = findViewById(R.id.btn_register)
        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.loginNow)
        textView.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonReg.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val email: String
            val password: String
            email = editTextEmail.text.toString()
            password = editTextPassword.text.toString()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this@RegisterActivity, "Enter email", Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this@RegisterActivity, "Enter password", Toast.LENGTH_SHORT).show()
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    progressBar.visibility = View.GONE
                    if (task.isSuccessful) {

                        Toast.makeText(
                            this@RegisterActivity,
                            "Account created.",
                            Toast.LENGTH_SHORT,
                        ).show()

                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {

                        Toast.makeText(
                            this@RegisterActivity,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }
        }

    }
}


