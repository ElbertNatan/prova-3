package com.example.imdlibrary

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.imdlibrary.databinding.ActivityRegisterUserBinding

class RegisterUserActivity : ComponentActivity() {
    private lateinit var binding: ActivityRegisterUserBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.registerButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (isUsernameTaken(username)) {
                Toast.makeText(this, "Nome de usuário já está em uso!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            registerUser(username, password)
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun isUsernameTaken(username: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_USERS,
            arrayOf(DatabaseHelper.USERNAME),
            "${DatabaseHelper.USERNAME} = ?",
            arrayOf(username),
            null,
            null,
            null
        )

        val isTaken = cursor.count > 0
        cursor.close()
        db.close()
        return isTaken
    }

    private fun registerUser(username: String, password: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.USERNAME, username)
            put(DatabaseHelper.PASSWORD, password)
        }

        val result = db.insert(DatabaseHelper.TABLE_USERS, null, values)
        if (result != -1L) {
            Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Erro ao cadastrar o usuário!", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }
}
