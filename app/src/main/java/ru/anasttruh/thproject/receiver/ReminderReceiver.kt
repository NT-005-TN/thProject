package ru.anasttruh.thproject.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.*
import ru.anasttruh.thproject.data.db.AppDatabase
import ru.anasttruh.thproject.util.Constants
import ru.anasttruh.thproject.util.NotificationHelper
import kotlin.coroutines.CoroutineContext

class ReminderReceiver : BroadcastReceiver(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO + SupervisorJob()

    override fun onReceive(context: Context, intent: Intent?) {
        val db = AppDatabase.getInstance(context)
        val carDao = db.carDao()
        val partDao = db.partDao()

        launch {
            // Получаем список автомобилей (синхронно)
            val cars = carDao.getAllCars()

            for (car in cars) {
                // Получаем детали для текущего автомобиля
                val parts = partDao.getPartsByCarId(car.id)

                for (part in parts) {
                    val wearRatio = part.hoursUsed.toFloat() / part.maxHours.toFloat()
                    if (wearRatio >= Constants.WEAR_THRESHOLD) {
                        withContext(Dispatchers.Main) {
                            NotificationHelper.showWearWarning(context, car.name, part.name)
                        }
                    }
                }
            }
        }
    }
}