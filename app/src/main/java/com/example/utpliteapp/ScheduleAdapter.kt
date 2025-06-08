package com.example.utpliteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class ScheduleAdapter(private val items: List<ScheduleItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDay: TextView = view.findViewById(R.id.tv_day)
    }

    inner class ClassViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemRoot: MaterialCardView = view.findViewById(R.id.item_root)
        val tvCourse: TextView = view.findViewById(R.id.tv_course_name)
        val tvInfo: TextView = view.findViewById(R.id.tv_course_info)
        val ivIcon: ImageView = view.findViewById(R.id.iv_icon)
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ScheduleItem.DayHeader -> TYPE_HEADER
            is ScheduleItem.ClassItem -> TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
            ClassViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is ScheduleItem.DayHeader -> {
                (holder as HeaderViewHolder).tvDay.text = item.day
            }
            is ScheduleItem.ClassItem -> {
                val classItem = item.schedule
                val h = holder as ClassViewHolder
                h.tvCourse.text = classItem.courseName
                h.tvInfo.text = "${classItem.startTime} - ${classItem.endTime} | ${classItem.location}"

                if (classItem.isVirtual) {
                    h.itemRoot.setBackgroundColor(ContextCompat.getColor(h.itemView.context, R.color.virtual_background))
                    h.ivIcon.setImageResource(R.drawable.ic_virtual)
                } else {
                    h.itemRoot.setBackgroundColor(ContextCompat.getColor(h.itemView.context, R.color.presencial_background))
                    h.ivIcon.setImageResource(R.drawable.ic_location)
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size
}
