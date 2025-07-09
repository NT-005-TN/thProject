package ru.anasttruh.thproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.anasttruh.thproject.R
import android.widget.Button
import ru.anasttruh.thproject.data.model.Car

class CarAdapter(
    private val onItemClick: (Car) -> Unit,
    private val onEditClick: (Car) -> Unit,
    private val onDeleteClick: (Car) -> Unit
) : ListAdapter<Car, CarAdapter.CarViewHolder>(CarDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val carNameTextView: TextView = itemView.findViewById(R.id.text_car_name)
        private val editButton: Button = itemView.findViewById(R.id.button_edit)

        fun bind(car: Car) {
            carNameTextView.text = car.name

            itemView.setOnClickListener { onItemClick(car) }
            editButton.setOnClickListener { onEditClick(car) }
        }
    }

    class CarDiffCallback : DiffUtil.ItemCallback<Car>() {
        override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean = oldItem == newItem
    }
}
