package ru.anasttruh.thproject.contract

import ru.anasttruh.thproject.data.model.Part

interface CarDetailsContract {
    interface View {
        fun showParts(parts: List<Part>)
        fun navigateToAddPart(carId: Long)
        fun navigateToEditPart(part: Part)
        fun showPartDetails(part: Part)
    }

    interface Presenter {
        fun loadParts()
        fun onAddPartClicked()
        fun onEditPartClicked(part: Part)
        fun onDeletePartClicked(part: Part)
        fun onPartSelected(part: Part)
        fun onDestroy()
    }
}

