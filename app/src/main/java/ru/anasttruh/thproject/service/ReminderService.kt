package ru.anasttruh.thproject.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import ru.anasttruh.thproject.receiver.ReminderReceiver

class ReminderService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        schedulePeriodicReminder(this)
        return START_NOT_STICKY
    }

    companion object {
        fun schedulePeriodicReminder(context: Context) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, ReminderReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            val interval = 6 * 60 * 60 * 1000L // 6 часов

            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + interval,
                interval,
                pendingIntent
            )
        }
    }
}
