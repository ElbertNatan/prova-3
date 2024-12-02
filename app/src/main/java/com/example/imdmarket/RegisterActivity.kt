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

            if (code.isEmpty()) {
                Toast.makeText(this, "Campo do código é obrigatório", Toast.LENGTH_SHORT).show()
            } else if (name.isEmpty()) {
                Toast.makeText(this, "Campo do nome é obrigatório", Toast.LENGTH_SHORT).show()
            } else if (description.isEmpty()) {
                Toast.makeText(this, "Campo da descrição é obrigatório", Toast.LENGTH_SHORT).show()
            } else if (stock == null) {
                Toast.makeText(this, "Campo de quantidade é obrigatório", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Produto Criado com sucesso", Toast.LENGTH_SHORT).show()
                finish()
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
