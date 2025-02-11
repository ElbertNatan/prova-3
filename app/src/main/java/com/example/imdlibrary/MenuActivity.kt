package com.example.imdlibrary

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.imdlibrary.databinding.ActivityMenuBinding

class MenuActivity : ComponentActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.editButton.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }

        binding.deleteButton.setOnClickListener {
            startActivity(Intent(this, DeleteActivity::class.java))
        }

        binding.listButton.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

        binding.logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}