package com.example.utpliteapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utpliteapp.ui.theme.UTPLiteAppTheme

class CalendarActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        recyclerView = findViewById(R.id.rv_schedule)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val scheduleList = listOf(
            ClassSchedule("Programación I", "Lunes", "8:00 AM", "10:00 AM", "Aula 101", false),
            ClassSchedule("Matemáticas Discretas", "Lunes", "10:00 AM", "12:00 PM", "Virtual", true),
            ClassSchedule("Estructura de Datos", "Martes", "2:00 PM", "4:00 PM", "Aula 204", false),
            ClassSchedule("Programación II", "Miércoles", "10:00 AM", "12:00 PM", "Virtual", true),
            ClassSchedule("Base de Datos", "Jueves", "4:00 PM", "6:00 PM", "Aula 303", false),
            ClassSchedule("Redes de Computadoras", "Viernes", "8:00 AM", "10:00 AM", "Virtual", true)
        )
        // Agrupar por día
                val scheduleItems = mutableListOf<ScheduleItem>()
                scheduleList.groupBy { it.day }.forEach { (day, classes) ->
                    scheduleItems.add(ScheduleItem.DayHeader(day))
                    scheduleItems.addAll(classes.map { ScheduleItem.ClassItem(it) })
                }

        // Pasar al adapter
                recyclerView.adapter = ScheduleAdapter(scheduleItems)

    }


}
