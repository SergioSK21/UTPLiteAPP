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
                Triple("Semana1", "Semana1.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana2", "Semana2.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana3", "Semana3.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Guia", "GuiaEstudio.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf")
            )
            "Paquete 2" -> listOf(
                Triple("Semana1", "Semana1.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana2", "Semana2.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana3", "Semana3.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Semana4", "Semana4.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                Triple("Guia", "GuiaEstudio.pdf", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf")
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