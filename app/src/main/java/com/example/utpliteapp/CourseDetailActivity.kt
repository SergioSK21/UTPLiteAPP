package com.example.utpliteapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CourseDetailActivity : AppCompatActivity() {

    // Nuevas estructuras
    data class CourseSection(val week: String, val sessions: List<Session>)
    data class Session(val title: String, val pdfUrl: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        val courseTitle = intent.getStringExtra("course_title")
        val professorName = intent.getStringExtra("professor_name")

        findViewById<TextView>(R.id.course_title).text = courseTitle
        findViewById<TextView>(R.id.professor_name).text = "Profesor: $professorName"

        val container = findViewById<LinearLayout>(R.id.sections_container)

        // Lista de semanas y sesiones con URL de PDF
        val sections = listOf(
            CourseSection("SEMANA 1", listOf(
                Session("S01.s1 – JavaScript: Conceptos, Tipos de Datos", "https://drive.google.com/uc?export=download&id=1Plwe7F6TOjGimZ1FhXLuoiGi0l6D-Fy8"),
                Session("S01.s2 – JavaScript: Conceptos, Tipos de Datos", "https://www.africau.edu/images/default/sample.pdf")
            )),
            CourseSection("SEMANA 2", listOf(
                Session("S02.s1 – Operadores en JavaScript", "https://www.africau.edu/images/default/sample.pdf"),
                Session("S02.s2 – Operadores en JavaScript", "https://www.africau.edu/images/default/sample.pdf")
            )),
            CourseSection("SEMANA 3", listOf(
                Session("S03.s1 – Manejo de Cadenas", "https://www.africau.edu/images/default/sample.pdf"),
                Session("S03.s2 – Manejo de Cadenas", "https://www.africau.edu/images/default/sample.pdf")
            )),
            CourseSection("SEMANA 4", listOf(
                Session("S04.s1 – Objetos y Arreglos", "https://www.africau.edu/images/default/sample.pdf"),
                Session("S04.s2 – Objetos y Arreglos", "https://www.africau.edu/images/default/sample.pdf")
            ))
        )

        for (section in sections) {
            val weekTitle = TextView(this).apply {
                text = section.week
                textSize = 18f
                setPadding(0, 16, 0, 8)
                setTextColor(resources.getColor(android.R.color.black))
            }

            container.addView(weekTitle)

            for (session in section.sessions) {
                val sessionText = TextView(this).apply {
                    text = session.title
                    textSize = 16f
                    setPadding(32, 8, 0, 8)
                    setBackgroundResource(R.drawable.rounded_background)
                    setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(session.pdfUrl))
                        startActivity(intent)
                    }
                }
                container.addView(sessionText)
            }
        }

        // Funcionalidad de los botones inferiores
        val homeButton = findViewById<Button>(R.id.btn_home)
        val zoomButton = findViewById<Button>(R.id.btn_zoom)
        val scheduleButton = findViewById<Button>(R.id.btn_schedule)

        homeButton.setOnClickListener {
            finish()
        }

        zoomButton.setOnClickListener {
            val zoomIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://zoom.us"))
            startActivity(zoomIntent)
        }

        scheduleButton.setOnClickListener {
            finish()
        }
    }
}

