// DbHelper.kt
package br.com.fiap.clarimail

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "EmailApp.db"

        // Tabela de e-mails
        private const val TABLE_EMAILS = "emails"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_SENDER = "sender"
        private const val COLUMN_SUBJECT = "subject"
        private const val COLUMN_CONTENT = "content"
        private const val COLUMN_TIMESTAMP = "timestamp"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_EMAILS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_SENDER TEXT,"
                + "$COLUMN_SUBJECT TEXT,"
                + "$COLUMN_CONTENT TEXT,"
                + "$COLUMN_TIMESTAMP INTEGER)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EMAILS")
        onCreate(db)
    }

    // Inserir um e-mail
    fun insertEmail(email: Email): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_SENDER, email.sender)
            put(COLUMN_SUBJECT, email.subject)
            put(COLUMN_CONTENT, email.content)
            put(COLUMN_TIMESTAMP, email.timestamp)
        }
        return db.insert(TABLE_EMAILS, null, values)
    }

    // Consultar todos os e-mails
    fun getAllEmails(): List<Email> {
        val emails = mutableListOf<Email>()
        val selectQuery = "SELECT * FROM $TABLE_EMAILS ORDER BY $COLUMN_TIMESTAMP DESC"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
            cursor?.let {
                if (it.moveToFirst()) {
                    do {
                        val id = it.getLong(it.getColumnIndex(COLUMN_ID))
                        val sender = it.getString(it.getColumnIndex(COLUMN_SENDER))
                        val subject = it.getString(it.getColumnIndex(COLUMN_SUBJECT))
                        val content = it.getString(it.getColumnIndex(COLUMN_CONTENT))
                        val timestamp = it.getLong(it.getColumnIndex(COLUMN_TIMESTAMP))
                        emails.add(Email(id, sender, subject, content, timestamp))
                    } while (it.moveToNext())
                }
            }
        } finally {
            cursor?.close()
        }

        return emails
    }

    // Atualizar um e-mail
    fun updateEmail(email: Email): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_SENDER, email.sender)
            put(COLUMN_SUBJECT, email.subject)
            put(COLUMN_CONTENT, email.content)
            put(COLUMN_TIMESTAMP, email.timestamp)
        }
        return db.update(TABLE_EMAILS, values, "$COLUMN_ID = ?", arrayOf(email.id.toString()))
    }

    // Deletar um e-mail
    fun deleteEmail(emailId: Long): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_EMAILS, "$COLUMN_ID = ?", arrayOf(emailId.toString()))
    }
}
