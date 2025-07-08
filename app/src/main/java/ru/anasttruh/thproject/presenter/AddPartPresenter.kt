package ru.anasttruh.thproject.presenter

import ru.anasttruh.thproject.contract.AddPartContract
import ru.anasttruh.thproject.data.db.PartDao
import ru.anasttruh.thproject.data.model.Part

import kotlinx.coroutines.*

class AddPartPresenter(
    private val view: AddPartContract.View,
    private val partDao: PartDao
) : AddPartContract.Presenter {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun savePart(carId: Long, name: String, used: Int, max: Int) {
        if (name.isBlank()) {
            view.showError("Название не может быть пустым")
            return
        }

        scope.launch {
            partDao.insert(Part(carId = carId, name = name, hoursUsed = used, maxHours = max))
            withContext(Dispatchers.Main) {
                view.finish()
            }
        }
    }

    override fun onDestroy() {
        scope.cancel()
    }
}
