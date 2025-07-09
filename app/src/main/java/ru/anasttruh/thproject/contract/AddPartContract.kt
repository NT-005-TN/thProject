package ru.anasttruh.thproject.contract

interface AddPartContract {
    interface View {
        fun showSuccess()
        fun showError(message: String)
        fun finish()
    }

    interface Presenter {
        fun savePart(carId: Long, name: String, hoursUsed: Int, maxHours: Int)
        fun onDestroy()
    }
}