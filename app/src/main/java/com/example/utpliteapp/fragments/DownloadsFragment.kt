package com.example.utpliteapp.fragments

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.utpliteapp.R
import com.google.android.material.snackbar.Snackbar
import java.io.File

class DownloadsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_downloads, container, false)

        val package1 = view.findViewById<View>(R.id.package_1)
        val package2 = view.findViewById<View>(R.id.package_2)
        val package3 = view.findViewById<View>(R.id.package_3)
        val package4 = view.findViewById<View>(R.id.package_4)

        package1.setOnClickListener { descargarPaquete("Paquete 1") }
        package2.setOnClickListener { descargarPaquete("Paquete 2") }
        package3.setOnClickListener { descargarPaquete("Paquete 3") }
        package4.setOnClickListener { descargarPaquete("Paquete 4") }

        return view
    }

    private fun descargarPaquete(nombrePaquete: String) {
        val ciclo = "Ciclo1"
        val curso = "CursoA"

        val archivos = when (nombrePaquete) {
            "Paquete 1" -> listOf(
                Triple("Semana1", "S01.s1 – Conceptos, Tipos de Datos.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana1", "S01.s2 – Conceptos, Tipos de Datos.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana2", "S02.s1 – Operadores en JavaScript.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana2", "S02.s2 – Operadores en JavaScript.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana3", "S03.s1 – Manejo de Cadenas.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana3", "S03.s2 – Manejo de Cadenas.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana4", "S04.s1 – Objetos y Arreglos.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana4", "S04.s2 – Objetos y Arreglos.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
            )
            "Paquete 2" -> listOf(
                Triple("Semana5", "S05.s1 – Creacion de algoritmos y programas con JS.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana5", "S05.s2 – Creacion de algoritmos y programas con JS.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana6", "S06.s1 – NodeJs.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana6", "S06.s2 – NodeJs.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana7", "S07.s1 – Angular.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana7", "S07.s2 – Angular.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana8", "S08.s1 – Elementos de Angular.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana8", "S08.s2 – Elementos de Angular.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf")
            )
            else -> emptyList()
        }

        if (archivos.isEmpty()) {
            Snackbar.make(requireView(), "No hay archivos para descargar", Snackbar.LENGTH_SHORT).show()
            return
        }

        val downloadManager = requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        archivos.forEach { (subcarpeta, nombreArchivo, url) ->
            val relativePath = "$ciclo/$curso/$nombrePaquete/$subcarpeta/"

            val request = DownloadManager.Request(Uri.parse(url)).apply {
                setTitle("Descargando $nombreArchivo")
                setDescription("Guardando en Descargas/$relativePath")
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                setAllowedOverMetered(true)
                setAllowedOverRoaming(true)
                setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$relativePath$nombreArchivo")
            }

            downloadManager.enqueue(request)
        }

        Snackbar.make(requireView(), "Descargas de $nombrePaquete iniciadas", Snackbar.LENGTH_SHORT).show()
    }


}