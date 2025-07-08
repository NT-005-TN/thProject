package ru.anasttruh.thproject.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "parts",
    foreignKeys = [ForeignKey(
        entity = Car::class,
        parentColumns = ["id"],
        childColumns = ["carId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Part(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val carId: Long,
    var name: String,
    var hoursUsed: Int,
    var maxHours: Int
)
