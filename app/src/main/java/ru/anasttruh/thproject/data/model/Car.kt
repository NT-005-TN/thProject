package ru.anasttruh.thproject.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "cars")
@Parcelize
data class Car(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var name: String
) : Parcelable
