package ru.anasttruh.thproject.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.anasttruh.thproject.data.model.Car

@Dao
interface CarDao {
    @Query("SELECT * FROM cars")
    fun getAll(): LiveData<List<Car>>

    @Insert
    suspend fun insert(car: Car): Long

    @Update
    suspend fun update(car: Car)

    @Delete
    suspend fun delete(car: Car)
}
