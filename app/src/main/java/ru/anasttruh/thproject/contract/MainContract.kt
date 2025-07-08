package ru.anasttruh.thproject.contract

import ru.anasttruh.thproject.data.model.Car

interface MainContract {
    interface View {
        fun showCars(cars: List<Car>)
        fun navigateToAddCar()
        fun navigateToEditCar(car: Car)
        fun navigateToCarDetails(carId: Long)
    }

    interface Presenter {
        fun loadCars()
        fun onAddCarClicked()
        fun onEditCarClicked(car: Car)
        fun onDeleteCarClicked(car: Car)
        fun onCarSelected(carId: Long)
        fun onDestroy()
    }
}

