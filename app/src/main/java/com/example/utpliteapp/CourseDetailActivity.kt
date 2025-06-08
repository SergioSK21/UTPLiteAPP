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

            // Contenedor por semana (LinearLayout vertical)
            val weekContainer = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 16, 0, 16)
                }
                setBackgroundResource(R.drawable.section_card_background) // tarjeta de semana
                setPadding(16, 16, 16, 16)
            }

            // Contenedor de sesiones
            val sessionsContainer = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                setPadding(8, 8, 8, 8)
                visibility = LinearLayout.GONE
            }

            // Header de la semana
            val weekTitle = TextView(this).apply {
                text = "📚  ${section.week}"  // Agregamos emoji para más visual
                textSize = 20f
                setPadding(8, 8, 8, 8)
                setTextColor(resources.getColor(android.R.color.white))
                setBackgroundResource(R.drawable.rounded_background_red)
            }

            // Listener toggle (expand/collapse)
            weekTitle.setOnClickListener {
                if (sessionsContainer.visibility == LinearLayout.GONE) {
                    sessionsContainer.visibility = LinearLayout.VISIBLE
                } else {
                    sessionsContainer.visibility = LinearLayout.GONE
                }
            }

            // Agregamos sesiones
            for (session in section.sessions) {
                val sessionText = TextView(this).apply {
                    text = "📄  ${session.title}"
                    textSize = 16f
                    setPadding(24, 16, 24, 16)
                    setBackgroundResource(R.drawable.session_item_background)
                    setTextColor(resources.getColor(android.R.color.black))
                    setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(session.pdfUrl))
                        startActivity(intent)
                    }
                }

                // Margen entre sesiones
                val sessionParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = 8
                }

                sessionText.layoutParams = sessionParams
                sessionsContainer.addView(sessionText)
            }

            // Armamos la estructura
            weekContainer.addView(weekTitle)
            weekContainer.addView(sessionsContainer)
            container.addView(weekContainer)
        }





    }
}

