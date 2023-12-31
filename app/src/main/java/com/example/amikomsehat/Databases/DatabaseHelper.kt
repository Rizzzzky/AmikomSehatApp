package com.example.amikomsehat

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class DatabaseHelper(val context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    companion object {
        private const val DATABASE_NAME = "amikomsehat"
        private const val DATABASE_VERSION = 2 // Incremented the version for the database upgrade

        // Table Account
        const val TABLE_ACCOUNT = "ACCOUNT"

        // Column Account table
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"

        // Table Doctor
        const val TABLE_DOCTOR = "DOCTOR"

        // Column Doctor table
        const val COLUMN_DOCTOR_ID = "doctor_id"
        const val COLUMN_DOCTOR_NAME = "doctor_name"
        const val COLUMN_DOCTOR_SPESIALIS = "doctor_spesialis"
        const val COLUMN_DOCTOR_IMAGE = "doctor_image"

        // Table Obat
        const val TABLE_Obat = "Obat"

        // Column Obat table
        const val COLUMN_Obat_ID = "Obat_id"
        const val COLUMN_Obat_NAME = "Obat_name"
        const val COLUMN_Obat_HARGA= "Obat_harga"
        const val COLUMN_Obat_IMAGE = "Obat_image"

        const val TABLE_CHAT_MESSAGE = "CHAT_MESSAGE"
        const val COLUMN_MESSAGE_ID = "message_id"
        const val COLUMN_MESSAGE_TEXT = "message_text"
        const val COLUMN_MESSAGE_TIMESTAMP = "message_timestamp"
        const val COLUMN_SENDER_ID = "sender_id"
        const val COLUMN_RECEIVER_ID = "receiver_id"
        const val COLUMN_CONVERSATION_ID = "conversation_id"
    }

    // Create table Account SQL query
    private val CREATE_ACCOUNT_TABLE = (
            "CREATE TABLE $TABLE_ACCOUNT ("
                    + "$COLUMN_USERNAME TEXT PRIMARY KEY, "
                    + "$COLUMN_PASSWORD TEXT)"
            )

    // Create table Doctor SQL query
    private val CREATE_DOCTOR_TABLE = (
            "CREATE TABLE $TABLE_DOCTOR ("
                    + "$COLUMN_DOCTOR_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "$COLUMN_DOCTOR_NAME TEXT, "
                    + "$COLUMN_DOCTOR_SPESIALIS TEXT, "
                    + "$COLUMN_DOCTOR_IMAGE BLOB)"
            )
    private val CREATE_Obat_TABLE = (
            "CREATE TABLE $TABLE_Obat ("
                    + "$COLUMN_Obat_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "$COLUMN_Obat_NAME TEXT, "
                    + "$COLUMN_Obat_HARGA TEXT, "
                    + "$COLUMN_Obat_IMAGE BLOB)"
            )
    private val CREATE_CHAT_MESSAGE_TABLE = (
            "CREATE TABLE $TABLE_CHAT_MESSAGE ("
                    + "$COLUMN_MESSAGE_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "$COLUMN_MESSAGE_TEXT TEXT, "
                    + "$COLUMN_MESSAGE_TIMESTAMP INTEGER, "
                    + "$COLUMN_SENDER_ID TEXT, "
                    + "$COLUMN_RECEIVER_ID TEXT, "
                    + "$COLUMN_CONVERSATION_ID TEXT)"
            )
    private val DROP_ACCOUNT_TABLE = "DROP TABLE IF EXISTS $TABLE_ACCOUNT"
    private val DROP_DOCTOR_TABLE = "DROP TABLE IF EXISTS $TABLE_DOCTOR"
    private val DROP_Obat_TABLE = "DROP TABLE IF EXISTS $TABLE_Obat"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_ACCOUNT_TABLE)
        db?.execSQL(CREATE_DOCTOR_TABLE)
        db?.execSQL(CREATE_Obat_TABLE)
        db?.execSQL(CREATE_CHAT_MESSAGE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_ACCOUNT_TABLE)
        db?.execSQL(DROP_DOCTOR_TABLE)
        db?.execSQL(DROP_Obat_TABLE)
        onCreate(db)
    }



    fun addDoctor(id: String, name: String, spesialis: String, image: Bitmap) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COLUMN_DOCTOR_ID, id)
        values.put(COLUMN_DOCTOR_NAME, name)
        values.put(COLUMN_DOCTOR_SPESIALIS, spesialis)

        // Convert bitmap to byte array
        val stream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()
        values.put(COLUMN_DOCTOR_IMAGE, byteArray)

        val result = db.insert(TABLE_DOCTOR, null, values)
        if (result == -1L) {
            Toast.makeText(context, "Failed to add doctor", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Doctor added successfully", Toast.LENGTH_SHORT).show()
        }

        db.close()
    }
    fun addObat(id: String, name: String, harga: String, image: Bitmap) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COLUMN_Obat_ID, id)
        values.put(COLUMN_Obat_NAME, name)
        values.put(COLUMN_Obat_HARGA, harga)


        val stream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()
        values.put(COLUMN_Obat_IMAGE, byteArray)

        val result = db.insert(TABLE_Obat, null, values)
        if (result == -1L) {
            Toast.makeText(context, "Failed to add Obat", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Obat added successfully", Toast.LENGTH_SHORT).show()
        }

        db.close()
    }


    @SuppressLint("Range")
    fun checkLogin(username: String, password: String): Boolean {
        val columns = arrayOf(COLUMN_USERNAME, COLUMN_PASSWORD)
        val db = this.readableDatabase
        // selection criteria
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        // selection arguments
        val selectionArgs = arrayOf(username, password)

        val cursor = db.query(
            TABLE_ACCOUNT, // table to query
            columns, // columns to return
            selection, // columns for WHERE clause
            selectionArgs, // the values for the WHERE clause
            null, // group the rows
            null, // filter by row groups
            null
        ) // the sort order

        val cursorCount = cursor.count
        cursor.close()
        db.close()
        // check data available or not
        if (cursorCount > 0)
            return true
        else
            return false
    }

    fun addAccount(username: String, password: String) {
        val db = this.writableDatabase

        val values = ContentValues()

        values.put(COLUMN_USERNAME, username)
        values.put(COLUMN_PASSWORD, password)
        val result = db.insert(TABLE_ACCOUNT, null, values)
        if (result == (0).toLong()) {

            Toast.makeText(context, "Register Failed", Toast.LENGTH_SHORT).show()
        } else {

            Toast.makeText(
                context, "Register Success, " +
                        "please login using your new account", Toast.LENGTH_SHORT
            ).show()
        }

        db.close()
    }

    @SuppressLint("Range")
    fun checkData(username: String): String {
        val colums = arrayOf(COLUMN_USERNAME)
        val db = this.readableDatabase
        val selection = "$COLUMN_USERNAME = ?"
        val selectionArgs = arrayOf(username)
        var name: String = ""

        val cursor = db.query(
            TABLE_ACCOUNT, //table to query
            colums, //columns to return
            selection, //columns for WHERE clause
            selectionArgs, //the values for the WHERE clause
            null, //group the rows
            null, //filter by row groups
            null
        ) //the sort order
        if (cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME))
        }

        cursor.close()
        return name
    }

    @SuppressLint("Range")
    fun showMenu(): ArrayList<DokterModel> {
        val listModel = ArrayList<DokterModel>()
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_DOCTOR, null)
        } catch (se: SQLiteException) {
            db.execSQL(CREATE_DOCTOR_TABLE)
            return ArrayList()
        }

        var id: Int
        var namaDokter: String
        var Spesialis: String
        var imageArray: ByteArray
        var imageBmp: Bitmap

        if (cursor.moveToFirst()) {
            do {
                //get data text
                id = cursor.getInt(cursor.getColumnIndex(COLUMN_DOCTOR_ID))
                namaDokter = cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_NAME))
                Spesialis = cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_SPESIALIS))
                //get data image
                imageArray = cursor.getBlob(cursor.getColumnIndex(COLUMN_DOCTOR_IMAGE))
                //convert ByteArray to Bitmap
                val byteInputStream = ByteArrayInputStream(imageArray)
                imageBmp = BitmapFactory.decodeStream(byteInputStream)
                val model = DokterModel(
                    id = id,
                    namaDokter = namaDokter,
                    Spesialis = Spesialis,
                    image = imageBmp
                )
                listModel.add(model)
            } while (cursor.moveToNext())
        }
        return listModel
    }
    @SuppressLint("Range")
    fun showObat(): ArrayList<ObatModel> {
        val listModel1 = ArrayList<ObatModel>()
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_Obat, null)
        } catch (se: SQLiteException) {
            db.execSQL(CREATE_Obat_TABLE)
            return ArrayList()
        }

        var id: Int
        var namaObat: String
        var hargaObat: String
        var imageArray: ByteArray
        var imageBmp: Bitmap

        if (cursor.moveToFirst()) {
            do {
                //get data text
                id = cursor.getInt(cursor.getColumnIndex(COLUMN_Obat_ID))
                namaObat = cursor.getString(cursor.getColumnIndex(COLUMN_Obat_NAME))
                hargaObat = cursor.getString(cursor.getColumnIndex(COLUMN_Obat_HARGA))
                //get data image
                imageArray = cursor.getBlob(cursor.getColumnIndex(COLUMN_Obat_IMAGE))
                //convert ByteArray to Bitmap
                val byteInputStream = ByteArrayInputStream(imageArray)
                imageBmp = BitmapFactory.decodeStream(byteInputStream)
                val model = ObatModel(
                    idObat = id,
                    namaObat = namaObat,
                    hargaObat = hargaObat,
                    imageObat = imageBmp
                )
                listModel1.add(model)
            } while (cursor.moveToNext())
        }
        return listModel1
    }

    fun deleteDokter(doctorId: Int): Boolean {
        val db = this.writableDatabase
        val selection = "$COLUMN_DOCTOR_ID = ?"
        val selectionArgs = arrayOf(doctorId.toString())
        val deletedRows = db.delete(TABLE_DOCTOR, selection, selectionArgs)
        db.close()
        return deletedRows > 0
    }
    fun deleteObat(ObatId: Int): Boolean {
        val db = this.writableDatabase
        val selection = "$COLUMN_Obat_ID = ?"
        val selectionArgs = arrayOf(ObatId.toString())
        val deletedRows = db.delete(TABLE_Obat, selection, selectionArgs)
        db.close()
        return deletedRows > 0
    }


}
