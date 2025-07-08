package ru.anasttruh.thproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carparts.databinding.ActivityEditPartBinding
import kotlinx.coroutines.*
import ru.anasttruh.thproject.data.db.AppDatabase
import ru.anasttruh.thproject.data.model.Part

class EditPartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditPartBinding
    private var part: Part? = null
    private val partDao by lazy { AppDatabase.getInstance(this).partDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        part = intent.getParcelableExtra("PART")

        part?.let {
            binding.editTextPartName.setText(it.name)
            binding.editTextUsedHours.setText(it.hoursUsed.toString())
            binding.editTextMaxHours.setText(it.maxHours.toString())
        }

        binding.buttonSavePart.setOnClickListener {
            part?.apply {
                name = binding.editTextPartName.text.toString()
                hoursUsed = binding.editTextUsedHours.text.toString().toIntOrNull() ?: 0
                maxHours = binding.editTextMaxHours.text.toString().toIntOrNull() ?: 100

                CoroutineScope(Dispatchers.IO).launch {
                    partDao.update(this@apply)
                    finish()
                }
            }
        }

        binding.buttonDeletePart.setOnClickListener {
            part?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    partDao.delete(it)
                    finish()
                }
            }
        }
    }
}
