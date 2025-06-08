package com.example.utpliteapp

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // MOSTRAR CÓDIGO DE BARRAS
        val showBarcodeButton = findViewById<ImageButton>(R.id.show_barcode_button)

        showBarcodeButton.setOnClickListener {
            // a la cocina
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.dialog_barcode, null)
            dialog.setContentView(view)

            // Configurar los elementos del dialog
            val barcodeImage = view.findViewById<ImageView>(R.id.dialog_barcode_image)
            val studentName = view.findViewById<TextView>(R.id.dialog_student_name)

            val studentCode = "1234567890"

            try {
                val bitmap = generateBarcode(studentCode)
                barcodeImage.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            studentName.text = "John Smith"

            // Mostrar el dialog
            dialog.show()
        }

        // MENU DE CURSOS
        val course1 = findViewById<CardView>(R.id.course_1)
        val course2 = findViewById<CardView>(R.id.course_2)
        val course3 = findViewById<CardView>(R.id.course_3)
        val course4 = findViewById<CardView>(R.id.course_4)
        val course5 = findViewById<CardView>(R.id.course_5)
        val course6 = findViewById<CardView>(R.id.course_6)

        course1.setOnClickListener {
            goToCourseDetail("Programación I", "Juan Pérez")
        }
        course2.setOnClickListener {
            goToCourseDetail("Matemáticas Discretas", "Ana Gómez")
        }
        course3.setOnClickListener {
            goToCourseDetail("Estructuras de Datos", "Carlos Ruiz")
        }
        course4.setOnClickListener {
            goToCourseDetail("Programación II", "Luis Miranda")
        }
        course5.setOnClickListener {
            goToCourseDetail("Bases de Datos", "María López")
        }
        course6.setOnClickListener {
            goToCourseDetail("Redes de Computadoras", "Ana Torres")
        }

        val courseImages = listOf(course1, course2, course3, course4, course5, course6)

        // BARRA INFERIOR
        val courses = findViewById<ImageView>(R.id.courses)
        val calendar = findViewById<ImageView>(R.id.calendar)
        val meetings = findViewById<ImageView>(R.id.meetings)
        val downloads = findViewById<ImageView>(R.id.downloads)

        courses.setOnClickListener {
            startActivity(Intent(this, CoursesActivity::class.java))
        }
        calendar.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }
        meetings.setOnClickListener {
            startActivity(Intent(this, MeetingsActivity::class.java))
        }
        downloads.setOnClickListener {
            startActivity(Intent(this, DownloadsActivity::class.java))
        }

        // BOTÓN AHORRO DE DATOS
        val btnDataSaver = findViewById<ImageButton>(R.id.data_saver_button)
        var isDataSaverOn = false

        btnDataSaver.setOnClickListener {
            isDataSaverOn = !isDataSaverOn

            if (isDataSaverOn) {
                for (card in courseImages) {
                    card.setCardBackgroundColor(resources.getColor(android.R.color.darker_gray))
                }


                Toast.makeText(this, "Modo ahorro de datos activado", Toast.LENGTH_SHORT).show()
            } else {
                course1.setCardBackgroundColor(resources.getColor(R.color.pastel_pink))
                course2.setCardBackgroundColor(resources.getColor(R.color.pastel_green))
                course3.setCardBackgroundColor(resources.getColor(R.color.pastel_blue))
                course4.setCardBackgroundColor(resources.getColor(R.color.pastel_yellow))
                course5.setCardBackgroundColor(resources.getColor(R.color.pastel_purple))
                course6.setCardBackgroundColor(resources.getColor(R.color.pastel_red))


                Toast.makeText(this, "Modo ahorro de datos desactivado", Toast.LENGTH_SHORT).show()
            }
        }

        // ANUNCIOS
        val announcementTitle = findViewById<TextView>(R.id.announcement_title)
        val announcementMessage = findViewById<TextView>(R.id.announcement_message)
        val announcementDate = findViewById<TextView>(R.id.announcement_date)

        announcementTitle.text = "Suspensión de clases"
        announcementMessage.text = "Las clases se suspenden este viernes por mantenimiento."
        announcementDate.text = "Fecha: 07 de junio de 2025"
    }

    private fun goToCourseDetail(courseName: String, professorName: String) {
        val intent = Intent(this, CourseDetailActivity::class.java)
        intent.putExtra("course_title", courseName)
        intent.putExtra("professor_name", professorName)
        startActivity(intent)
    }

    private fun generateBarcode(data: String): Bitmap {
        val width = 600
        val height = 300
        val bitMatrix: BitMatrix =
            MultiFormatWriter().encode(data, BarcodeFormat.CODE_128, width, height)
        val pixels = IntArray(width * height)

        for (y in 0 until height) {
            for (x in 0 until width) {
                pixels[y * width + x] =
                    if (bitMatrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt()
            }
        }

        return Bitmap.createBitmap(pixels, width, height, Bitmap.Config.ARGB_8888)
    }
}
