package ru.anasttruh.thproject.presenter

import kotlinx.coroutines.*
import ru.anasttruh.thproject.contract.CarDetailsContract
import ru.anasttruh.thproject.data.db.PartDao
import ru.anasttruh.thproject.data.model.Part
import ru.anasttruh.thproject.view.CarDetailsActivity
import ru.anasttruh.thproject.view.CarDetailsView

class CarDetailsPresenter(
    private val view: CarDetailsView,
    private val partDao: PartDao,
    private val carId: Long
) : CarDetailsContract.Presenter {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun loadParts() {
        scope.launch {
            val parts = partDao.getPartsByCarId(carId) // suspend функция
            withContext(Dispatchers.Main) {
                view.showParts(parts)
            }
        }
    }

    override fun onAddPartClicked() {
        view.navigateToAddPart(carId)
    }

    override fun onEditPartClicked(part: Part) {
        view.navigateToEditPart(part)
    }

    override fun onDeletePartClicked(part: Part) {
        scope.launch {
            partDao.delete(part)
        }
    }

    override fun onPartSelected(part: Part) {
        view.showPartDetails(part)
    }

    override fun onDestroy() {
        scope.cancel()
    }
}