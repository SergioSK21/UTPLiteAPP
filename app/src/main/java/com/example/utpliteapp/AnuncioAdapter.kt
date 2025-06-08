package com.example.utpliteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class AnuncioAdapter(private val anunciosOriginales: List<AdActivity.Anuncio>) :
    RecyclerView.Adapter<AnuncioAdapter.AnuncioViewHolder>() {

    private var anunciosFiltrados = anunciosOriginales.toMutableList()
    private val posicionesExpandidas = mutableSetOf<Int>()

    inner class AnuncioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.tvTitulo)
        val fecha: TextView = view.findViewById(R.id.tvFecha)
        val categoria: TextView = view.findViewById(R.id.tvCategoria)
        val descripcion: TextView = view.findViewById(R.id.tvDescripcion)
        val layoutDescripcion: View = view.findViewById(R.id.layoutDescripcion)
        val btnExpandir: ImageButton = view.findViewById(R.id.btnExpandir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnuncioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_anuncio, parent, false)
        return AnuncioViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnuncioViewHolder, position: Int) {
        val anuncio = anunciosFiltrados[position]

        holder.titulo.text = anuncio.titulo
        holder.fecha.text = anuncio.fecha
        holder.categoria.text = anuncio.categoria
        holder.descripcion.text = anuncio.descripcion

        val expandido = posicionesExpandidas.contains(position)
        holder.layoutDescripcion.visibility = if (expandido) View.VISIBLE else View.GONE
        holder.btnExpandir.rotation = if (expandido) 180f else 0f

        holder.itemView.setOnClickListener {
            if (expandido) {
                posicionesExpandidas.remove(position)
            } else {
                posicionesExpandidas.add(position)
            }
            notifyItemChanged(position)
        }

        holder.btnExpandir.setOnClickListener {
            toggleExpand(position)
        }
    }

    override fun getItemCount(): Int = anunciosFiltrados.size

    private fun toggleExpand(position: Int) {
        if (posicionesExpandidas.contains(position)) {
            posicionesExpandidas.remove(position)
        } else {
            posicionesExpandidas.add(position)
        }
        notifyItemChanged(position)
    }

    fun filtrar(texto: String) {
        val textoLower = texto.lowercase()
        anunciosFiltrados = if (texto.isBlank()) {
            anunciosOriginales.toMutableList()
        } else {
            anunciosOriginales.filter {
                it.titulo.lowercase().contains(textoLower) ||
                        it.descripcion.lowercase().contains(textoLower)
            }.toMutableList()
        }
        posicionesExpandidas.clear()
        notifyDataSetChanged()
    }
}
