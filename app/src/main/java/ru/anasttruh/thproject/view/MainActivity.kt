package ru.anasttruh.thproject.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.anasttruh.thproject.adapter.CarAdapter
import ru.anasttruh.thproject.contract.MainContract
import ru.anasttruh.thproject.data.db.AppDatabase
import ru.anasttruh.thproject.data.model.Car
import ru.anasttruh.thproject.databinding.ActivityMainBinding
import ru.anasttruh.thproject.presenter.MainPresenter
import ru.anasttruh.thproject.service.ReminderService
import ru.anasttruh.thproject.util.NotificationHelper

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: CarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(
            this,
            AppDatabase.getInstance(this).carDao()
        )

        adapter = CarAdapter(
            onItemClick = { presenter.onCarSelected(it.id) },
            onEditClick = { presenter.onEditCarClicked(it) },
            onDeleteClick = { presenter.onDeleteCarClicked(it) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            presenter.onAddCarClicked()
        }

        NotificationHelper.createChannel(this)
        ReminderService.schedulePeriodicReminder(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.loadCars()
    }

    override fun showCars(cars: List<Car>) {
        adapter.submitList(cars)
    }

    override fun navigateToAddCar() {
        startActivity(Intent(this, AddCarActivity::class.java))
    }

    override fun navigateToEditCar(car: Car) {
        val intent = Intent(this, EditCarActivity::class.java).apply {
            putExtra("CAR", car)
        }
        startActivity(intent)
    }

    override fun navigateToCarDetails(carId: Long) {
        val intent = Intent(this, CarDetailsActivity::class.java).apply {
            putExtra("CAR_ID", carId)
        }
        startActivity(intent)
    }

    override fun finish() {
        super.finish()
    }
}