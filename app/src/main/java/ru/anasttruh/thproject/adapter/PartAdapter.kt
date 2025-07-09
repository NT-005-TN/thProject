package ru.anasttruh.thproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.anasttruh.thproject.R
import ru.anasttruh.thproject.data.model.Part

class PartAdapter(
    private val onItemClick: (Part) -> Unit,
    private val onEditClick: (Part) -> Unit,
    private val onDeleteClick: (Part) -> Unit
) : ListAdapter<Part, PartAdapter.PartViewHolder>(PartDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_part, parent, false)
        return PartViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val partNameText: TextView = itemView.findViewById(R.id.text_part_name)
        private val partUsageText: TextView = itemView.findViewById(R.id.text_part_usage)
        private val warningText: TextView = itemView.findViewById(R.id.text_part_warning)
        private val editButton: Button = itemView.findViewById(R.id.button_edit_part)

        fun bind(part: Part) {
            partNameText.text = part.name
            partUsageText.text = "${part.hoursUsed} / ${part.maxHours} ч"

            val wearRatio = part.hoursUsed.toFloat() / part.maxHours.toFloat()
            warningText.visibility = if (wearRatio >= 0.8f) View.VISIBLE else View.GONE

            itemView.setOnClickListener { onItemClick(part) }
            editButton.setOnClickListener { onEditClick(part) }
        }
    }

    class PartDiffCallback : DiffUtil.ItemCallback<Part>() {
        override fun areItemsTheSame(oldItem: Part, newItem: Part): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Part, newItem: Part): Boolean = oldItem == newItem
    }
}
