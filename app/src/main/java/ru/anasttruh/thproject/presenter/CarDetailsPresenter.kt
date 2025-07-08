package ru.anasttruh.thproject.presenter

import kotlinx.coroutines.*
import ru.anasttruh.thproject.contract.CarDetailsContract
import ru.anasttruh.thproject.data.db.PartDao

class CarDetailsPresenter(
    private val view: CarDetailsContract.View,
    private val partDao: PartDao,
    private val carId: Long
) : CarDetailsContract.Presenter {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun loadParts() {
        view.observeParts(partDao.getByCarId(carId)) // LiveData
    }

    override fun onAddPartClick() {
        view.navigateToAddPart(carId)
    }

    override fun onEditPartClick(part: ru.anasttruh.thproject.data.model.Part) {
        view.navigateToEditPart(part)
    }

    override fun onDeletePartClick(part: ru.anasttruh.thproject.data.model.Part) {
        scope.launch {
            withContext(Dispatchers.IO) {
                partDao.delete(part)
            }
        }
    }

    override fun onDestroy() {
        scope.cancel()
    }
}
