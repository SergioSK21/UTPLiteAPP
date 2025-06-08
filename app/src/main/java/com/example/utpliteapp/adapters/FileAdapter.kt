package com.example.utpliteapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.utpliteapp.R
import com.example.utpliteapp.models.FileItem
import com.example.utpliteapp.FilesActivity

class FileAdapter(
    private val files: List<FileItem>,
    private val onDownloadClick: (FileItem) -> Unit
) : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    class FileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fileName: TextView = view.findViewById(R.id.fileName)
        val downloadBtn: Button = view.findViewById(R.id.downloadBtn)
        val cardView: CardView = view.findViewById(R.id.file_card) // ← Asegúrate que esto exista en XML
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_file, parent, false)
        return FileViewHolder(view)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val file = files[position]
        holder.fileName.text = file.name

        holder.downloadBtn.setOnClickListener {
            onDownloadClick(file)
        }

        holder.cardView.setOnClickListener {
            val intent = Intent(holder.cardView.context, FilesActivity::class.java)
            intent.putExtra("course_name", file.name) // Usa file.name
            holder.cardView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = files.size
}
