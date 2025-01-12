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

        // Botão para deletar o produto
        binding.deleteButton.setOnClickListener {
            val isbn = binding.code.text.toString()

            if (isbn.isEmpty()) {
                Toast.makeText(this, "Por favor, insira o ISBN do produto.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = dbHelper.writableDatabase
            val rowsDeleted = db.delete(
                DatabaseHelper.TABLE_BOOKS,
                "${DatabaseHelper.ISBN} = ?",
                arrayOf(isbn)
            )
            db.close()

            if (rowsDeleted > 0) {
                Toast.makeText(this, "Produto deletado com sucesso!", Toast.LENGTH_SHORT).show()
                binding.code.text.clear()
            } else {
                Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_SHORT).show()
            }
        }

        // Botão para limpar os campos
        binding.clearButton.setOnClickListener {
            binding.code.text.clear()
        }

        // Botão para voltar ao menu
        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }
}
