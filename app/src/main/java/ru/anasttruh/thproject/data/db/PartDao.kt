package ru.anasttruh.thproject.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.anasttruh.thproject.data.model.Part

@Dao
interface PartDao {
    @Query("SELECT * FROM parts WHERE carId = :carId")
    fun getByCarId(carId: Long): LiveData<List<Part>>

    @Insert
    suspend fun insert(part: Part)

    @Update
    suspend fun update(part: Part)

    @Delete
    suspend fun delete(part: Part)
}
