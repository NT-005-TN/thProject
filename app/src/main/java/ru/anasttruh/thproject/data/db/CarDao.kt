package ru.anasttruh.thproject.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.anasttruh.thproject.data.model.Car

@Dao
interface CarDao {
    // Асинхронный доступ через LiveData (для UI)
    @Query("SELECT * FROM cars")
    fun getAll(): LiveData<List<Car>>

    // Синхронный доступ (для работы в корутинах или BroadcastReceivers)
    @Query("SELECT * FROM cars")
    suspend fun getAllCars(): List<Car>

    @Insert
    suspend fun insert(car: Car): Long

    @Update
    suspend fun update(car: Car)

    @Delete
    suspend fun delete(car: Car)
}