package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val codeField = findViewById<EditText>(R.id.code)
        val nameField = findViewById<EditText>(R.id.name)
        val descriptionField = findViewById<EditText>(R.id.description)
        val stockField = findViewById<EditText>(R.id.stock)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val clearButton = findViewById<Button>(R.id.clearButton)

        saveButton.setOnClickListener {
            val code = codeField.text.toString()
            val name = nameField.text.toString()
            val description = descriptionField.text.toString()
            val stock = stockField.text.toString().toIntOrNull()

            if (code.isEmpty() || name.isEmpty() || stock == null) {
                Toast.makeText(this, "Preencha todos os campos obrigatórios!", Toast.LENGTH_SHORT).show()
            } else {
                val produto = Produto(code, name, description, stock)
                val app = application as ProductApp

                if (app.listaProdutos.any { it.codigo == code }) {
                    Toast.makeText(this, "Produto com este código já existe!", Toast.LENGTH_SHORT).show()
                } else {
                    app.listaProdutos.add(produto)
                    Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

        clearButton.setOnClickListener {
            codeField.text.clear()
            nameField.text.clear()
            descriptionField.text.clear()
            stockField.text.clear()
        }

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
