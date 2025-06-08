package com.example.utpliteapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CourseDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        val courseTitle = intent.getStringExtra("course_title")
        val professorName = intent.getStringExtra("professor_name")

        val titleTextView = findViewById<TextView>(R.id.course_title)
        val professorTextView = findViewById<TextView>(R.id.professor_name)

        titleTextView.text = courseTitle
        professorTextView.text = "Profesor: $professorName"
    }
}