package com.example.imdlibrary

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.imdlibrary.databinding.ActivityBookDetailsBinding
import com.squareup.picasso.Picasso

class BookDetailsActivity : ComponentActivity() {
    private lateinit var binding: ActivityBookDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("TITLE") ?: "Título não disponível"
        val author = intent.getStringExtra("AUTHOR") ?: "Autor não disponível"
        val publisher = intent.getStringExtra("PUBLISHER") ?: "Editora não disponível"
        val year = intent.getStringExtra("YEAR") ?: "Ano não disponível"
        val description = intent.getStringExtra("DESCRIPTION") ?: "Descrição não disponível"
        val imageUrl = intent.getStringExtra("IMAGE_URL")

        binding.bookTitle.text = title
        binding.bookAuthor.text = "Autor: $author"
        binding.bookPublisher.text = "Editora: $publisher"
        binding.bookYear.text = "Ano: $year"
        binding.bookDescription.text = description

        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(binding.bookImage)

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
            finish()
        }
    }
}