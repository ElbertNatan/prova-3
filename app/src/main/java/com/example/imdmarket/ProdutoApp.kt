package com.example.imdmarket

import android.app.Application
import java.io.File

class ProductApp : Application() {
    val listaProdutos = mutableSetOf<Produto>()
    private val fileName = "produtos.txt"

    override fun onCreate() {
        super.onCreate()
        carregarProdutos()
    }

    private fun carregarProdutos() {
        val file = File(filesDir, fileName)
        if (file.exists()) {
            file.forEachLine { line ->
                val parts = line.split(";")
                if (parts.size == 4) {
                    val produto = Produto(parts[0], parts[1], parts[2], parts[3].toInt())
                    listaProdutos.add(produto)
                }
            }
        }
    }

    fun salvarProdutos() {
        val file = File(filesDir, fileName)
        file.printWriter().use { out ->
            listaProdutos.forEach { produto ->
                out.println("${produto.codigo};${produto.nome};${produto.descricao};${produto.estoque}")
            }
        }
    }
}
