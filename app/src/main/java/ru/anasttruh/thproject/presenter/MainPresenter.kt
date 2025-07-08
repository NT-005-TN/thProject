package ru.anasttruh.thproject.presenter

import kotlinx.coroutines.*
import ru.anasttruh.thproject.contract.MainContract
import ru.anasttruh.thproject.data.db.CarDao
import kotlin.coroutines.CoroutineContext

class MainPresenter(
    private val view: MainContract.View,
    private val carDao: CarDao
) : MainContract.Presenter, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    override fun loadCars() {
        view.observeCars(carDao.getAll()) // LiveData
    }

    override fun onCarClick(carId: Long) {
        view.navigateToCarDetails(carId)
    }

    override fun onAddCarClick() {
        view.navigateToAddCar()
    }

    override fun onEditCarClick(car: ru.anasttruh.thproject.data.model.Car) {
        view.navigateToEditCar(car)
    }

    override fun onDeleteCarClick(car: ru.anasttruh.thproject.data.model.Car) {
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
