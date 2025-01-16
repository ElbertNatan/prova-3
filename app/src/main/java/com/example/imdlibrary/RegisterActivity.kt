package com.example.imdlibrary

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.imdlibrary.databinding.ActivityRegisterBinding

class RegisterActivity : ComponentActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val isbn = binding.code.text.toString()
            val title = binding.title.text.toString()
            val author = binding.author.text.toString()
            val publisher = binding.publisher.text.toString()
            val description = binding.description.text.toString()
            val year = binding.year.text.toString()
            val url = binding.url.text.toString()

            if (isbn.isEmpty() || title.isEmpty()) {
                Toast.makeText(this, "ISBN e Título são obrigatórios!", Toast.LENGTH_SHORT).show()
            } else {
                val db = dbHelper.writableDatabase
                val values = ContentValues().apply {
                    put(DatabaseHelper.ISBN, isbn)
                    put(DatabaseHelper.TITLE, title)
                    put(DatabaseHelper.AUTHOR, author)
                    put(DatabaseHelper.PUBLISHER, publisher)
                    put(DatabaseHelper.DESCRIPTION, description)
                    put(DatabaseHelper.YEAR, year)
                    put(DatabaseHelper.URL, url)
                }

                val result = db.insert(DatabaseHelper.TABLE_BOOKS, null, values)

                if (result == -1L) {
                    Toast.makeText(this, "Erro ao cadastrar o livro!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Livro cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    clearFields()
                }
                db.close()
            }
        }

        binding.clearButton.setOnClickListener { clearFields() }
        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }

    private fun clearFields() {
        binding.code.text.clear()
        binding.title.text.clear()
        binding.author.text.clear()
        binding.publisher.text.clear()
        binding.description.text.clear()
        binding.year.text.clear()
        binding.url.text.clear()
    }
}
