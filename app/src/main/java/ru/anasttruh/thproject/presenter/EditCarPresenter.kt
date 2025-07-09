package ru.anasttruh.thproject.presenter

import kotlinx.coroutines.*
import ru.anasttruh.thproject.contract.EditCarContract
import ru.anasttruh.thproject.data.db.CarDao
import ru.anasttruh.thproject.data.model.Car

class EditCarPresenter(
    private val view: EditCarContract.View,
    private val carDao: CarDao,
    private val car: Car
) : EditCarContract.Presenter {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun loadCar(car: Car) {
        // Можно игнорировать аргумент, так как car уже передан через конструктор
        view.showCar(car)
    }

    override fun updateCar(name: String) {
        if (name.isBlank()) {
            view.showError("Название не может быть пустым")
            return
        }

        scope.launch {
            car.name = name
            carDao.update(car)
            withContext(Dispatchers.Main) {
                view.finish()
            }
        }
    }

    override fun deleteCar() {
        scope.launch {
            carDao.delete(car)
            withContext(Dispatchers.Main) {
                view.finish()
            }
        }
    }

    override fun onDestroy() {
        scope.cancel()
    }
}