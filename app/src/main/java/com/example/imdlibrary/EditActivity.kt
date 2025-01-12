package com.example.imdlibrary

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.imdlibrary.databinding.ActivityEditBinding

class EditActivity : ComponentActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        // Botão para carregar os dados de um livro
        binding.loadButton.setOnClickListener {
            val isbn = binding.code.text.toString()

            if (isbn.isEmpty()) {
                Toast.makeText(this, "Por favor, insira o ISBN do livro.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = dbHelper.readableDatabase
            val cursor = db.query(
                DatabaseHelper.TABLE_BOOKS,
                null,
                "${DatabaseHelper.ISBN} = ?",
                arrayOf(isbn),
                null,
                null,
                null
            )

            if (cursor.moveToFirst()) {
                binding.name.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.TITLE)))
                binding.author.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.AUTHOR)))
                binding.publisher.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PUBLISHER)))
                binding.description.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DESCRIPTION)))
                binding.url.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.URL)))
                Toast.makeText(this, "Livro carregado com sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Livro não encontrado!", Toast.LENGTH_SHORT).show()
            }
            cursor.close()
            db.close()
        }

        // Botão para atualizar os dados de um livro
        binding.editButton.setOnClickListener {
            val isbn = binding.code.text.toString()
            val title = binding.name.text.toString()
            val author = binding.author.text.toString()
            val publisher = binding.publisher.text.toString()
            val description = binding.description.text.toString()
            val url = binding.url.text.toString()

            if (isbn.isEmpty() || title.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha o ISBN e o título.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(DatabaseHelper.TITLE, title)
                put(DatabaseHelper.AUTHOR, author)
                put(DatabaseHelper.PUBLISHER, publisher)
                put(DatabaseHelper.DESCRIPTION, description)
                put(DatabaseHelper.URL, url)
            }

            val rowsUpdated = db.update(
                DatabaseHelper.TABLE_BOOKS,
                values,
                "${DatabaseHelper.ISBN} = ?",
                arrayOf(isbn)
            )

            if (rowsUpdated > 0) {
                Toast.makeText(this, "Livro atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                clearFields()
            } else {
                Toast.makeText(this, "Erro ao atualizar o livro!", Toast.LENGTH_SHORT).show()
            }
            db.close()
        }

        // Botão para limpar os campos
        binding.clearButton.setOnClickListener { clearFields() }

        // Botão para voltar ao menu
        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }

    private fun clearFields() {
        binding.code.text.clear()
        binding.name.text.clear()
        binding.author.text.clear()
        binding.publisher.text.clear()
        binding.description.text.clear()
        binding.url.text.clear()
    }
}
