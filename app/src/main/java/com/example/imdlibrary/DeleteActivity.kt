package com.example.imdlibrary

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.imdlibrary.databinding.ActivityDeleteBinding

class DeleteActivity : ComponentActivity() {
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.deleteButton.setOnClickListener {
            val isbn = binding.code.text.toString()

            if (isbn.isEmpty()) {
                Toast.makeText(this, "Por favor, insira o ISBN do livro.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = dbHelper.writableDatabase
            val rowsDeleted = db.delete(
                DatabaseHelper.TABLE_BOOKS,
                "${DatabaseHelper.ISBN} = ?",
                arrayOf(isbn)
            )

            if (rowsDeleted > 0) {
                Toast.makeText(this, "Livro deletado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Livro não encontrado!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.clearButton.setOnClickListener {
            binding.code.text.clear()
        }

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }
}
