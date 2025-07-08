package ru.anasttruh.thproject.view


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carparts.R
import com.example.carparts.databinding.ActivityMainBinding
import ru.anasttruh.thproject.adapter.CarAdapter
import ru.anasttruh.thproject.data.db.AppDatabase
import ru.anasttruh.thproject.data.model.Car
import ru.anasttruh.thproject.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainView {

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
            onItemClick = { presenter.onCarSelected(it) },
            onEditClick = { presenter.onEditCarClicked(it) },
            onDeleteClick = { presenter.onDeleteCarClicked(it) }
        )

        binding.recyclerCars.layoutManager = LinearLayoutManager(this)
        binding.recyclerCars.adapter = adapter

        binding.fabAddCar.setOnClickListener {
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
        val intent = Intent(this, EditCarActivity::class.java)
        intent.putExtra("CAR", car)
        startActivity(intent)
    }

    override fun navigateToCarDetails(carId: Long) {
        val intent = Intent(this, CarDetailsActivity::class.java)
        intent.putExtra("CAR_ID", carId)
        startActivity(intent)
    }
}