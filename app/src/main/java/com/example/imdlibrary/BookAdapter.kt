package com.example.imdlibrary

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

data class Book(
    val isbn: String,
    val title: String,
    val author: String,
    val publisher: String,
    val description: String,
    val year: String,
    val imageUrl: String
)

class BookAdapter(private val books: List<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookImage: ImageView = view.findViewById(R.id.bookImage)
        val bookTitle: TextView = view.findViewById(R.id.bookTitle)
        val bookYear: TextView = view.findViewById(R.id.bookYear)
        val bookPublisher: TextView = view.findViewById(R.id.bookPublisher)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bookTitle.text = book.title
        holder.bookYear.text = "Ano: ${book.year}"
        holder.bookPublisher.text = "Editora: ${book.publisher}"
        Picasso.get().load(book.imageUrl).placeholder(R.drawable.ic_launcher_background).into(holder.bookImage)

        // Configura o clique para abrir a tela de detalhes
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, BookDetailsActivity::class.java).apply {
                putExtra("TITLE", book.title)
                putExtra("AUTHOR", book.author)
                putExtra("PUBLISHER", book.publisher)
                putExtra("YEAR", book.year)
                putExtra("DESCRIPTION", book.description)
                putExtra("IMAGE_URL", book.imageUrl)
            }
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int = books.size
}
