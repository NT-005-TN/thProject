package ru.anasttruh.thproject.view

import ru.anasttruh.thproject.data.model.Part

interface CarDetailsView {
    fun showParts(parts: List<Part>)
    fun navigateToAddPart(carId: Long)
    fun navigateToEditPart(part: Part)
    fun showPartDetails(part: Part)
}