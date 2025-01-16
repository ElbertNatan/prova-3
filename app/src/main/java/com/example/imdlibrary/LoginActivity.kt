package com.example.imdlibrary

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.imdlibrary.databinding.ActivityLoginBinding

class LoginActivity : ComponentActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.loginButton.setOnClickListener {
            val usernameField = binding.username.text.toString()
            val passwordField = binding.password.text.toString()

            if (usernameField.isEmpty() || passwordField.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (isValidUser(usernameField, passwordField)) {
                startActivity(Intent(this, MenuActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Credenciais inválidas!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registerUser.setOnClickListener {
            startActivity(Intent(this, RegisterUserActivity::class.java))
        }

        binding.forgotLogin.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
    }

    private fun isValidUser(username: String, password: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_USERS,
            null,
            "${DatabaseHelper.USERNAME} = ? AND ${DatabaseHelper.PASSWORD} = ?",
            arrayOf(username, password),
            null,
            null,
            null
        )

        val isValid = cursor.count > 0
        cursor.close()
        db.close()
        return isValid
    }
}
