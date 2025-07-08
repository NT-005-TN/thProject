package ru.anasttruh.thproject.view

import ru.anasttruh.thproject.data.model.Part
import ru.anasttruh.thproject.data.db.AppDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carparts.databinding.ActivityAddPartBinding
import kotlinx.coroutines.*

class AddPartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPartBinding
    private val partDao by lazy { AppDatabase.getInstance(this).partDao() }
    private var carId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carId = intent.getLongExtra("CAR_ID", -1L)

        binding.buttonSavePart.setOnClickListener {
            val name = binding.editTextPartName.text.toString()
            val used = binding.editTextUsedHours.text.toString().toIntOrNull() ?: 0
            val max = binding.editTextMaxHours.text.toString().toIntOrNull() ?: 100

            CoroutineScope(Dispatchers.IO).launch {
                partDao.insert(
                    Part(carId = carId, name = name, hoursUsed = used, maxHours = max)
                )
                finish()
            }
        }
    }
}
