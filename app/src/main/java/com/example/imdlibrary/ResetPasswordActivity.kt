package com.example.imdlibrary

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.imdlibrary.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : ComponentActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.resetButton.setOnClickListener {
            val username = binding.username.text.toString()
            val oldPassword = binding.oldPassword.text.toString()
            val newPassword = binding.newPassword.text.toString()

            if (username.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (isOldPasswordCorrect(username, oldPassword)) {
                updatePassword(username, newPassword)
            } else {
                Toast.makeText(this, "Senha antiga incorreta!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun isOldPasswordCorrect(username: String, oldPassword: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_USERS,
            null,
            "${DatabaseHelper.USERNAME} = ? AND ${DatabaseHelper.PASSWORD} = ?",
            arrayOf(username, oldPassword),
            null,
            null,
            null
        )

        val isValid = cursor.count > 0
        cursor.close()
        db.close()
        return isValid
    }

    private fun updatePassword(username: String, newPassword: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.PASSWORD, newPassword)
        }

        val rowsUpdated = db.update(
            DatabaseHelper.TABLE_USERS,
            values,
            "${DatabaseHelper.USERNAME} = ?",
            arrayOf(username)
        )

        if (rowsUpdated > 0) {
            Toast.makeText(this, "Senha redefinida com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Erro ao redefinir a senha!", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }
}
