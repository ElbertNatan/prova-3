package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.ComponentActivity

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val listView = findViewById<ListView>(R.id.product_list)
        val backButton = findViewById<Button>(R.id.backButton)

        // Obtenha os produtos da lista global
        val produtos = (application as ProductApp).listaProdutos.toList()

        // Transforme os produtos em um formato de string para exibir no ListView
        val productNames = produtos.map { produto ->
            "Código: ${produto.codigo}\nNome: ${produto.nome}\nEstoque: ${produto.estoque}"
        }

        // Configurar o adapter para exibir os produtos no ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, productNames)
        listView.adapter = adapter

        // Lógica do botão "Voltar"
        backButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish() // Finaliza a ListActivity para evitar acumular Activities na pilha
        }
    }
}
