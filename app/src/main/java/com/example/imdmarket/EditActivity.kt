package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class EditActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val codeField = findViewById<EditText>(R.id.code)
        val nameField = findViewById<EditText>(R.id.name)
        val descriptionField = findViewById<EditText>(R.id.description)
        val stockField = findViewById<EditText>(R.id.stock)
        val editButton = findViewById<Button>(R.id.editButton)
        val clearButton = findViewById<Button>(R.id.clearButton)

        val loadButton = findViewById<Button>(R.id.loadButton)
        loadButton.setOnClickListener {
            val code = codeField.text.toString()
            val app = application as ProductApp

            val produto = app.listaProdutos.find { it.codigo == code }
            if (produto != null) {
                nameField.setText(produto.nome)
                descriptionField.setText(produto.descricao)
                stockField.setText(produto.estoque.toString())
                Toast.makeText(this, "Produto carregado com sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_SHORT).show()
            }
        }

        editButton.setOnClickListener {
            val code = codeField.text.toString()
            val name = nameField.text.toString()
            val description = descriptionField.text.toString()
            val stock = stockField.text.toString().toIntOrNull()

            if (code.isEmpty() || name.isEmpty() || stock == null) {
                Toast.makeText(this, "Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show()
            } else {
                val app = application as ProductApp
                val produto = app.listaProdutos.find { it.codigo == code }

                if (produto != null) {
                    app.listaProdutos.remove(produto)
                    app.listaProdutos.add(Produto(code, name, description, stock))
                    Toast.makeText(this, "Produto alterado com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_SHORT).show()
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
