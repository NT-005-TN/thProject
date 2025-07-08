package ru.anasttruh.thproject.contract

import ru.anasttruh.thproject.data.model.Car


interface EditCarContract {
    interface View {
        fun showCar(car: Car)
        fun showSuccess()
        fun showError(message: String)
    }

    interface Presenter {
        fun loadCar(car: Car)
        fun updateCar(name: String)
        fun deleteCar()
        fun onDestroy()
    }
}
