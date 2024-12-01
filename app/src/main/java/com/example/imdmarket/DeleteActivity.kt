package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class DeleteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val codeField = findViewById<EditText>(R.id.code)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val backButton = findViewById<Button>(R.id.backButton)
        val clearButton = findViewById<Button>(R.id.clearButton)

        deleteButton.setOnClickListener {
            val code = codeField.text.toString()
            val app = application as ProductApp

            if (code.isEmpty()) {
                Toast.makeText(this, "Código do produto é obrigatório!", Toast.LENGTH_SHORT).show()
            } else {
                val produto = app.listaProdutos.find { it.codigo == code }
                if (produto != null) {
                    app.listaProdutos.remove(produto)
                    Toast.makeText(this, "Produto deletado com sucesso!", Toast.LENGTH_SHORT).show()
                    codeField.text.clear()
                } else {
                    Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        clearButton.setOnClickListener {
            codeField.text.clear()
        }
    }
}
