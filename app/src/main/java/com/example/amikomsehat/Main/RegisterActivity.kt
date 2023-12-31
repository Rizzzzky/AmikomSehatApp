package com.example.amikomsehat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val txtName: EditText = findViewById(R.id.registeruserName)
        val txtPassword: EditText = findViewById(R.id.registerPassword)
        //instance button register
        val btnRegister: Button = findViewById(R.id.buttonRegisterAccount)
        btnRegister.setOnClickListener {
            //object class databaseHelper
            val databaseHelper = DatabaseHelper(this)
            //declare data

            val name:String = txtName.text.toString().trim()
            val password:String = txtPassword.text.toString().trim()

            //insert data
            databaseHelper.addAccount(name,password)

            //show LoginActivity
            val intentLogin = Intent(this@RegisterActivity,
                LoginActivity::class.java)
            startActivity(intentLogin)


        }
    }
}