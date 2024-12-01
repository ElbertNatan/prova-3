package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.imdmarket.databinding.ActivityEditBinding
import androidx.activity.ComponentActivity

class EditActivity : ComponentActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadButton.setOnClickListener {
            val code = binding.code.text.toString()
            val app = application as ProductApp

            val produto = app.listaProdutos.find { it.codigo == code }
            if (produto != null) {
                binding.name.setText(produto.nome)
                binding.description.setText(produto.descricao)
                binding.stock.setText(produto.estoque.toString())
                Toast.makeText(this, "Produto carregado com sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.editButton.setOnClickListener {
            val code = binding.code.text.toString()
            val name = binding.name.text.toString()
            val description = binding.description.text.toString()
            val stock = binding.stock.text.toString().toIntOrNull()

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

        binding.clearButton.setOnClickListener {
            binding.code.text.clear()
            binding.name.text.clear()
            binding.description.text.clear()
            binding.stock.text.clear()
        }

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }
}
