package ru.anasttruh.thproject.contract

interface AddPartContract {
    interface View {
        fun showSuccess()
        fun showError(message: String)
    }

    interface Presenter {
        fun savePart(name: String, hoursUsed: Int, maxHours: Int)
        fun onDestroy()
    }
}
