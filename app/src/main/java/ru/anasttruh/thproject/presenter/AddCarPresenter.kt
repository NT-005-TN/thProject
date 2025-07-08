package ru.anasttruh.thproject.presenter

import kotlinx.coroutines.*
import ru.anasttruh.thproject.contract.AddCarContract
import ru.anasttruh.thproject.data.db.CarDao
import ru.anasttruh.thproject.data.model.Car

class AddCarPresenter(
    private val view: AddCarContract.View,
    private val carDao: CarDao
) : AddCarContract.Presenter {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun saveCar(name: String) {
        if (name.isBlank()) {
            view.showError("Название не может быть пустым")
            return
        }

        scope.launch {
            carDao.insert(Car(name = name))
            withContext(Dispatchers.Main) {
                view.finish()
            }
        }
    }

    override fun onDestroy() {
        scope.cancel()
    }
}
