package com.example.amikomsehat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txtUsername: EditText = findViewById(R.id.editTextUsername)
        val txtPassword: EditText = findViewById(R.id.editTextPassword)
        val txtLinkRegister: TextView = findViewById(R.id.btnRegister)
        val btnLogin: Button = findViewById(R.id.buttonLogin)

        txtLinkRegister.setOnClickListener {
            val intentRegister = Intent(
                this@LoginActivity,
                RegisterActivity::class.java
            )
            startActivity(intentRegister)
        }

        btnLogin.setOnClickListener {
            val databaseHelper = DatabaseHelper(this)

            val username = txtUsername.text.toString().trim()
            val password = txtPassword.text.toString().trim()

            val result: Boolean = databaseHelper.checkLogin(username, password)

            if (result) {
                Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT).show()

                if (username == "admin" && password == "admin") {
                    val intentCrud = Intent(this@LoginActivity, CrudActivity::class.java)
                    intentCrud.putExtra("IS_ADMIN", true)
                    startActivity(intentCrud)
                } else {
                    val intentLogin = Intent(this@LoginActivity, MainActivity::class.java)
                    intentLogin.putExtra("USERNAME_EXTRA", username)
                    startActivity(intentLogin)
                }

            } else {
                Toast.makeText(
                    this@LoginActivity,
                    "Login Failed, Try Again !!!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}
