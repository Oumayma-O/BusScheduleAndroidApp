package com.example.tp3

import android.app.Application
import com.example.tp3.database.AppDatabase

class BusScheduleApplication : Application() {

    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
}