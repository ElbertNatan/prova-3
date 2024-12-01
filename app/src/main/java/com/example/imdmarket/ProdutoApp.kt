package com.example.imdmarket

import android.app.Application

class ProductApp : Application() {
    val listaProdutos = mutableSetOf<Produto>()
}
