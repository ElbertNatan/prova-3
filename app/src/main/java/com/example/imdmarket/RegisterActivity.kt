package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.imdmarket.databinding.ActivityRegisterBinding

class RegisterActivity : ComponentActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val code = binding.code.text.toString()
            val name = binding.name.text.toString()
            val description = binding.description.text.toString()
            val stock = binding.stock.text.toString().toIntOrNull()

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

        binding.clearButton.setOnClickListener {
            binding.code.text.clear()
            binding.name.text.clear()
            binding.description.text.clear()
            binding.stock.text.clear()
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
