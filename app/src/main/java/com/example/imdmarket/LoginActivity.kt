package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.imdmarket.databinding.ActivityLoginBinding
import androidx.activity.ComponentActivity

class LoginActivity : ComponentActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", "admin")
        editor.putString("password", "admin")
        editor.apply()

        binding.loginButton.setOnClickListener {
            val usernameField = binding.username.text.toString()
            val passwordField = binding.password.text.toString()
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
