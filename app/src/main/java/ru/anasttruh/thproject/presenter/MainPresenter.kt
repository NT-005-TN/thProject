package ru.anasttruh.thproject.presenter

import kotlinx.coroutines.*
import ru.anasttruh.thproject.contract.MainContract
import ru.anasttruh.thproject.data.db.CarDao
import ru.anasttruh.thproject.data.model.Car
import ru.anasttruh.thproject.view.MainActivity
import kotlin.coroutines.CoroutineContext

class MainPresenter(
    private val view: MainActivity,
    private val carDao: CarDao
) : MainContract.Presenter, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    override fun loadCars() {
        launch {
            val cars = withContext(Dispatchers.IO) {
                carDao.getAllCars()
            }
            view.showCars(cars)
        }
    }


    override fun onCarSelected(carId: Long) {
        view.navigateToCarDetails(carId)
    }

    override fun onAddCarClicked() {
        view.navigateToAddCar()
    }

    override fun onEditCarClicked(car: ru.anasttruh.thproject.data.model.Car) {
        view.navigateToEditCar(car)
    }

    override fun onDeleteCarClicked(car: ru.anasttruh.thproject.data.model.Car) {
        launch {
            withContext(Dispatchers.IO) {
                carDao.delete(car)
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
    }
}