package com.example.kamulahtv

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kamulahtv.network.Program
import java.util.*

class ProgramAdapter : ListAdapter<Program, ProgramAdapter.ProgramViewHolder>(ProgramDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        return ProgramViewHolder.create(parent)
    }
    private val cal = Calendar.getInstance()
    private val hour = cal.get(Calendar.HOUR)

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        val current = getItem(position)
        val title = current.title
        val day = current.day
        val startTime = current.startTime
        val endTime = current.endTime

        if (startTime != null) {
            if (day != null) {
                if (endTime != null) {
                    holder.bind(title, day, startTime, endTime)
                }
            }
        }
    }
    class ProgramViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val title: TextView = itemView.findViewById(R.id.textProgram)
        private val day: TextView = itemView.findViewById(R.id.textDay)


        @SuppressLint("SetTextI18n")
        fun bind(titleText: String, dayText: String, startTimeText: String, endTimeText: String){
            title.text = titleText
            day.text = "${dayText}: $startTimeText to $endTimeText"

        }
        companion object{
            fun create(parent: ViewGroup) : ProgramViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false)
                return ProgramViewHolder(view)
            }
        }
    }
    object ProgramDiffCallback : DiffUtil.ItemCallback<Program>() {
        override fun areItemsTheSame(oldItem: Program, newItem: Program): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Program, newItem: Program): Boolean {
            return oldItem.title == newItem.title
        }
    }


}