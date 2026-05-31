package com.mobile.bcsit6th

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoursesActivity : AppCompatActivity() {

    private lateinit var rvCourses: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: CoursesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        rvCourses = findViewById(R.id.rvCourses)
        progressBar = findViewById(R.id.progressBar)
        rvCourses.layoutManager = LinearLayoutManager(this)

        // Fetch data from real API using Retrofit
        fetchCoursesFromApi()
    }

    private fun fetchCoursesFromApi() {
        progressBar.visibility = View.VISIBLE
        
        val apiService = ApiService.create()
        apiService.getCourses().enqueue(object : Callback<List<Course>> {
            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val courses = response.body() ?: emptyList()
                    adapter = CoursesAdapter(courses)
                    rvCourses.adapter = adapter
                } else {
                    Toast.makeText(this@CoursesActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@CoursesActivity, "Failure: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}