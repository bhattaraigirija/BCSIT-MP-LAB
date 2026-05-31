package com.mobile.bcsit6th

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoursesAdapter(private val courses: List<Course>) :
    RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvCourseTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvCourseDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        holder.tvTitle.text = course.title
        holder.tvDescription.text = course.description
    }

    override fun getItemCount() = courses.size
}