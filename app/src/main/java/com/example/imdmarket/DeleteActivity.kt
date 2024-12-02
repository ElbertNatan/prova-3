package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.imdmarket.databinding.ActivityDeleteBinding
import androidx.activity.ComponentActivity

class DeleteActivity : ComponentActivity() {
    private lateinit var binding: ActivityDeleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val code = binding.code.text.toString()

            if (code.isEmpty()) {
                Toast.makeText(this, "Código do produto é obrigatório!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Produto deletado com sucesso!", Toast.LENGTH_SHORT).show()
                binding.code.text.clear()
                startActivity(Intent(this, MenuActivity::class.java))
                finish()
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
