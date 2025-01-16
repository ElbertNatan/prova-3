package com.example.imdlibrary

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdlibrary.databinding.ActivityListBinding

class ListActivity : ComponentActivity() {
    private lateinit var binding: ActivityListBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        val bookList = getBooksFromDatabase()
        val adapter = BookAdapter(bookList)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }

    private fun getBooksFromDatabase(): List<Book> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_BOOKS,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val books = mutableListOf<Book>()
        while (cursor.moveToNext()) {
            val book = Book(
                isbn = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ISBN)),
                title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.TITLE)),
                author = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.AUTHOR)),
                publisher = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PUBLISHER)),
                description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DESCRIPTION)),
                year = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.YEAR)),
                imageUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.URL))
            )
            books.add(book)
        }
        cursor.close()
        db.close()
        return books
    }
}
