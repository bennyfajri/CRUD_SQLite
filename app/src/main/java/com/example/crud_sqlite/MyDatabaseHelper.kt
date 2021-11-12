package com.example.crud_sqlite

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "BookLibrary.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "my_library"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_TITLE = "book_title"
        private const val COLUMN_AUTHOR = "book_author"
        private const val COLUMN_PAGES = "book_pages"
    }

    override fun onCreate(db : SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITLE TEXT, $COLUMN_AUTHOR TEXT, $COLUMN_PAGES INTEGER);"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, i: Int, i1: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addBook(
        title: String,
        author: String,
        pages: Int
    ){
        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_TITLE, title)
        cv.put(COLUMN_AUTHOR, author)
        cv.put(COLUMN_PAGES, pages)
        val result = db.insert(TABLE_NAME,null, cv)
        if((-1).toLong() == result){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show()
        }
    }

    fun readAllData(): Cursor? {
        val query = "SELECT * FROM $TABLE_NAME"
        val db: SQLiteDatabase = this.readableDatabase

        var cursor: Cursor? = null
        if(db != null){
            cursor = db.rawQuery(query, null)
        }
        return cursor

    }

    fun updateData(row_id: String, title: String, author: String, pages: String){
        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_TITLE, title)
        cv.put(COLUMN_AUTHOR, author)
        cv.put(COLUMN_PAGES, pages)

        val result: Long = db.update(TABLE_NAME, cv, "_id=?", arrayOf(row_id)).toLong()
        if((-1).toLong() == result){
            Toast.makeText(context, "Failed update", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Successully update", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteOneData(row_id: String){
        val db: SQLiteDatabase = this.writableDatabase
        val result: Long = db.delete(TABLE_NAME, "_id=?", arrayOf(row_id)).toLong()
        if(result == (-1).toLong()){
            Toast.makeText(context, "Failed to delete data", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Successfully to delete data", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteAllData(){
        val db: SQLiteDatabase = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
    }
}