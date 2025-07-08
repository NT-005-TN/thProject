package ru.anasttruh.thproject.contract

import ru.anasttruh.thproject.data.model.Part

interface EditPartContract {
    interface View {
        fun showPart(part: Part)
        fun showSuccess()
        fun showError(message: String)
    }

    interface Presenter {
        fun loadPart(part: Part)
        fun updatePart(name: String, hoursUsed: Int, maxHours: Int)
        fun deletePart()
        fun onDestroy()
    }
}
