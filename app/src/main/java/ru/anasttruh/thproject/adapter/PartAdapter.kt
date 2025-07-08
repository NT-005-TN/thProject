package ru.anasttruh.thproject.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.carparts.R
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
        fun bind(part: Part) {
            itemView.text_part_name.text = part.name
            itemView.text_part_usage.text = "${part.hoursUsed} / ${part.maxHours} ч"

            val wearRatio = part.hoursUsed.toFloat() / part.maxHours
            itemView.text_part_warning.visibility =
                if (wearRatio >= 0.8f) View.VISIBLE else View.GONE

            itemView.setOnClickListener {
                onItemClick(part)
            }

            itemView.button_edit_part.setOnClickListener {
                onEditClick(part)
            }

            itemView.button_delete_part.setOnClickListener {
                onDeleteClick(part)
            }
        }
    }

    class PartDiffCallback : DiffUtil.ItemCallback<Part>() {
        override fun areItemsTheSame(oldItem: Part, newItem: Part): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Part, newItem: Part): Boolean = oldItem == newItem
    }
}
