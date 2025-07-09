package ru.anasttruh.thproject.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.anasttruh.thproject.adapter.PartAdapter
import ru.anasttruh.thproject.presenter.CarDetailsPresenter
import ru.anasttruh.thproject.data.db.AppDatabase
import ru.anasttruh.thproject.data.model.Part
import ru.anasttruh.thproject.databinding.ActivityCarDetailsBinding

class CarDetailsActivity : AppCompatActivity(), CarDetailsView {

    private lateinit var binding: ActivityCarDetailsBinding
    private lateinit var presenter: CarDetailsPresenter
    private lateinit var adapter: PartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val carId = intent.getLongExtra("CAR_ID", -1L)
        if (carId == -1L) finish()

        val partDao = AppDatabase.getInstance(this).partDao()

        presenter = CarDetailsPresenter(this, partDao, carId)

        adapter = PartAdapter(
            onItemClick = { presenter.onPartSelected(it) },
            onEditClick = { presenter.onEditPartClicked(it) },
            onDeleteClick = { presenter.onDeletePartClicked(it) }
        )

        binding.recyclerViewParts.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewParts.adapter = adapter

        binding.fabAddPart.setOnClickListener {
            presenter.onAddPartClicked()
        }

        presenter.loadParts()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadParts()  // обновляем список при возвращении на экран
    }

    override fun showParts(parts: List<Part>) {
        adapter.submitList(parts)
    }

    override fun navigateToAddPart(carId: Long) {
        val intent = Intent(this, AddPartActivity::class.java)
        intent.putExtra("CAR_ID", carId)
        startActivity(intent)
    }

    override fun navigateToEditPart(part: Part) {
        val intent = Intent(this, EditPartActivity::class.java).apply {
            putExtra("PART", part)
        }
        startActivity(intent)
    }

    override fun showPartDetails(part: Part) {
        // Можно открыть диалог или новую активити
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}