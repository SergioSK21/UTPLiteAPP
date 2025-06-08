package com.example.utpliteapp

import DestacadoAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class AdActivity : AppCompatActivity() {

    data class Anuncio(
        val titulo: String,
        val fecha: String,
        val descripcion: String,
        val categoria: String = "GENERAL"
    )

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnuncioAdapter
    private lateinit var etBuscar: EditText
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad) // Asegúrate de que esto va primero

        // Banners destacados
        viewPager = findViewById(R.id.viewPagerDestacados)
        val imagenesDestacadas = listOf(
            "https://portal.utp.edu.pe/A191/banner/c6da038d-008d-47fe-8080-bcdc0109bb41/493.jpg",
            "https://utp-prd-upload-file-storage.s3.amazonaws.com/pao/content/b859fbe7-3800-4bef-8ba4-b982a0d08890/BANNER%20WEB%20-%20BUS%20NO%20TE%20CONFORMES_BRGPBM.jpg",
            "https://utp-prd-upload-file-storage.s3.amazonaws.com/pao/content/6dec1b73-2426-4e7f-b179-ecb9169efe68/Banner-Class%20-%20Entel_EXTKZM.png"
        )

        viewPager.adapter = DestacadoAdapter(imagenesDestacadas)

        val handler = Handler(Looper.getMainLooper())
        var currentPage = 0
        handler.post(object : Runnable {
            override fun run() {
                if (currentPage >= imagenesDestacadas.size) currentPage = 0
                viewPager.setCurrentItem(currentPage++, true)
                handler.postDelayed(this, 4000)
            }
        })

        // Encuentra el botón cerrar y el layout contenedor
        val btnCerrarDestacados = findViewById<ImageButton>(R.id.btnCerrarDestacados)
        val layoutAnunciosDestacados = findViewById<LinearLayout>(R.id.layoutAnunciosDestacados)

// Configura el click listener para ocultar el layout al presionar
        btnCerrarDestacados.setOnClickListener {
            layoutAnunciosDestacados.visibility = View.GONE
        }


        // Anuncios
        etBuscar = findViewById(R.id.etBuscar)
        recyclerView = findViewById(R.id.recyclerViewAnuncios)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listaAnuncios = listOf(
            Anuncio("Inicio de Clases", "08/07/2025", "Las clases comienzan este lunes. Prepárate."),
            Anuncio("Examen Parcial", "15/07/2025", "Revisa tu aula virtual para conocer tu horario."),
            Anuncio("Charla de Egresados", "20/06/2025", "Participa y conoce cómo ingresar al mercado laboral.")
        )

        adapter = AnuncioAdapter(listaAnuncios)
        recyclerView.adapter = adapter

        etBuscar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filtrar(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
