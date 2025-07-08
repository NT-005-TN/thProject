package ru.anasttruh.thproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.carparts.R
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
        fun bind(car: Car) {
            itemView.text_car_name.text = car.name

            itemView.setOnClickListener {
                onItemClick(car)
            }

            itemView.button_edit_car.setOnClickListener {
                onEditClick(car)
            }

            itemView.button_delete_car.setOnClickListener {
                onDeleteClick(car)
            }
        }
    }

    class CarDiffCallback : DiffUtil.ItemCallback<Car>() {
        override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean = oldItem == newItem
    }
}