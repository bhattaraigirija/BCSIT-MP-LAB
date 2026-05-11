package com.mobile.bcsit6th

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class StudentListActivity : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        listView = findViewById(R.id.listViewStudents)
        databaseHelper = DatabaseHelper(this)
        val studentList = databaseHelper.getAllStudents()
        val dataList = ArrayList<String>()

        for(student in studentList) {
            dataList.add(
                "Name : ${student.name}\n" +
                        "Email : ${student.email}\n" +
                        "Address : ${student.address}\n" +
                        "Phone : ${student.phone}\n" +
                        "Gender : ${student.gender}"
            )
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            dataList
        )

        listView.adapter = adapter
    }
}