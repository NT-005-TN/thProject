package ru.anasttruh.thproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carparts.databinding.ActivityEditCarBinding
import kotlinx.coroutines.*
import ru.anasttruh.thproject.data.db.AppDatabase
import ru.anasttruh.thproject.data.model.Car

class EditCarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditCarBinding
    private var car: Car? = null
    private val carDao by lazy { AppDatabase.getInstance(this).carDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        car = intent.getParcelableExtra("CAR")

        car?.let {
            binding.editTextCarName.setText(it.name)
        }

        binding.buttonSaveCar.setOnClickListener {
            car?.let {
                it.name = binding.editTextCarName.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    carDao.update(it)
                    finish()
                }
            }
        }

        binding.buttonDeleteCar.setOnClickListener {
            car?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    carDao.delete(it)
                    finish()
                }
            }
        }
    }
}
