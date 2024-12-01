package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", "admin")
        editor.putString("password", "admin")
        editor.apply()

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            val usernameField = findViewById<EditText>(R.id.username).text.toString()
            val passwordField = findViewById<EditText>(R.id.password).text.toString()
            val username = sharedPreferences.getString("username", "")
            val password = sharedPreferences.getString("password", "")

            if (username == usernameField && password == passwordField) {
                startActivity(Intent(this, MenuActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Credenciais inválidas!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
