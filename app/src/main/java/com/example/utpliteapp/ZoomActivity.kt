package com.example.utpliteapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ZoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom)

        val joinMeetingButton = findViewById<Button>(R.id.join_meeting_button)

        joinMeetingButton.setOnClickListener {

            Toast.makeText(this, "Uniéndose a la reunión de Zoom...", Toast.LENGTH_SHORT).show()

            val zoomUrl = "https://utpvirtual.zoom.us/rec/play/wrs3AlBUOXP_8tqJDp2DnPjHa9HJSeuoeFaBGPX9ZEBzNKTbjP_Dslsgxg6AWFCfQMjn3JVn0NIKlh6i.39lgkwRMy3oWmEsQ"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(zoomUrl))
            startActivity(intent)
        }
    }
}
