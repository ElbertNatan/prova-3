package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val editButton = findViewById<Button>(R.id.editButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val listButton = findViewById<Button>(R.id.listButton)

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        editButton.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }

        deleteButton.setOnClickListener {
            startActivity(Intent(this, DeleteActivity::class.java))
        }

        listButton.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
    }
}
