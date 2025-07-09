package ru.anasttruh.thproject.presenter

import kotlinx.coroutines.*
import ru.anasttruh.thproject.contract.EditPartContract
import ru.anasttruh.thproject.data.db.PartDao
import ru.anasttruh.thproject.data.model.Part

class EditPartPresenter(
    private val view: EditPartContract.View,
    private val partDao: PartDao,
    private val part: Part
) : EditPartContract.Presenter {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun loadPart(part: Part) {
        // Можно проигнорировать аргумент, так как часть уже передана через конструктор
        view.showPart(part)
    }

    override fun updatePart(name: String, used: Int, max: Int) {
        if (name.isBlank()) {
            view.showError("Название не может быть пустым")
            return
        }

        scope.launch {
            part.name = name
            part.hoursUsed = used
            part.maxHours = max
            partDao.update(part)
            withContext(Dispatchers.Main) {
                view.finish()
            }
        }
    }

    override fun deletePart() {
        scope.launch {
            partDao.delete(part)
            withContext(Dispatchers.Main) {
                view.finish()
            }
        }
    }

    override fun onDestroy() {
        scope.cancel()
    }
}