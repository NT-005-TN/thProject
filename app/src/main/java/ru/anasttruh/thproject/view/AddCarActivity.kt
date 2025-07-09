package ru.anasttruh.thproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import ru.anasttruh.thproject.data.db.AppDatabase
import ru.anasttruh.thproject.data.model.Car
import ru.anasttruh.thproject.databinding.ActivityAddCarBinding

class AddCarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCarBinding
    private val carDao by lazy { AppDatabase.getInstance(this).carDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSaveCar.setOnClickListener {
            val name = binding.editTextCarName.text.toString().trim()
            if (name.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    carDao.insert(Car(name = name))
                    finish()
                }
            }
        }
    }
}
