package com.mobile.bcsit6th
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "StudentDB", null, 1) {

    companion object {
        const val TABLE_NAME = "students"
        const val ID = "id"
        const val NAME = "name"
        const val EMAIL = "email"
        const val ADDRESS = "address"
        const val PHONE = "phone"
        const val GENDER = "gender"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = """
            CREATE TABLE $TABLE_NAME(
                $ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $NAME TEXT,
                $EMAIL TEXT,
                $ADDRESS TEXT,
                $PHONE TEXT,
                $GENDER TEXT
            )
        """.trimIndent()

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Insert Data
    fun insertStudent(
        name: String,
        email: String,
        address: String,
        phone: String,
        gender: String
    ): Boolean {
        val db = writableDatabase
        val values = ContentValues()

        values.put(NAME, name)
        values.put(EMAIL, email)
        values.put(ADDRESS, address)
        values.put(PHONE, phone)
        values.put(GENDER, gender)

        val result = db.insert(TABLE_NAME, null, values)
        return result != -1L
    }

    // Get All Data
    fun getAllStudents(): ArrayList<StudentModel> {
        val studentList = ArrayList<StudentModel>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {

            do {

                val student = StudentModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
                )

                studentList.add(student)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return studentList
    }


}