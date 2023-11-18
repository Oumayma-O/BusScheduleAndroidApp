package com.example.tp3.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "stop_name")
    val stopName: String,

    @ColumnInfo(name = "arrival_time")
    val arrivalTime: Int
)