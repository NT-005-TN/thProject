package ru.anasttruh.thproject.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.anasttruh.thproject.data.model.Car
import ru.anasttruh.thproject.data.model.Part

@Database(entities = [Car::class, Part::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
    abstract fun partDao(): PartDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "car_parts.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
