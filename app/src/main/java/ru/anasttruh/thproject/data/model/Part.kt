package ru.anasttruh.thproject.data.model

import androidx.room.*
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "parts",
    foreignKeys = [ForeignKey(
        entity = Car::class,
        parentColumns = ["id"],
        childColumns = ["carId"],
        onDelete = ForeignKey.CASCADE
    )]
)
@Parcelize
data class Part(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val carId: Long,
    var name: String,
    var hoursUsed: Int,
    var maxHours: Int
) : Parcelable