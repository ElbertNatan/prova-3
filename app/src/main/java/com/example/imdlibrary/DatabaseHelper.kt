package com.example.imdlibrary

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "IMDLibrary.db"
        const val DATABASE_VERSION = 2 // Atualize a versão do banco para 2

        const val TABLE_BOOKS = "books"
        const val TABLE_USERS = "users"

        // Colunas de livros
        const val ISBN = "isbn"
        const val TITLE = "title"
        const val AUTHOR = "author"
        const val PUBLISHER = "publisher"
        const val DESCRIPTION = "description"
        const val YEAR = "year"
        const val URL = "url"

        // Colunas de usuários
        const val USER_ID = "id"
        const val USERNAME = "username"
        const val PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createBooksTable = """
            CREATE TABLE $TABLE_BOOKS (
                $ISBN TEXT PRIMARY KEY,
                $TITLE TEXT NOT NULL,
                $AUTHOR TEXT DEFAULT '',
                $PUBLISHER TEXT DEFAULT '',
                $DESCRIPTION TEXT DEFAULT '',
                $YEAR TEXT DEFAULT '',
                $URL TEXT DEFAULT ''
            )
        """.trimIndent()

        val createUsersTable = """
            CREATE TABLE $TABLE_USERS (
                $USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $USERNAME TEXT UNIQUE NOT NULL,
                $PASSWORD TEXT NOT NULL
            )
        """.trimIndent()

        db.execSQL(createBooksTable)
        db.execSQL(createUsersTable)

        // Inserir usuário padrão
        db.execSQL("INSERT INTO $TABLE_USERS ($USERNAME, $PASSWORD) VALUES ('admin', 'admin')")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            // Adiciona a coluna YEAR na tabela de livros
            db.execSQL("ALTER TABLE $TABLE_BOOKS ADD COLUMN $YEAR TEXT DEFAULT ''")
        }
    }
}
