package ru.anasttruh.thproject.contract

interface AddCarContract {
    interface View {
        fun showSuccess()
        fun showError(message: String)
        fun finish()
    }

    interface Presenter {
        fun saveCar(name: String)
        fun onDestroy()
    }
}
