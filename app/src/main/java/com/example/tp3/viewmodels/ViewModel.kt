package com.example.tp3.viewmodels

import androidx.lifecycle.ViewModel
import com.example.tp3.database.entities.ScheduleDao

class BusScheduleViewModel(private val scheduleDao: ScheduleDao) : ViewModel() {

    fun fullSchedule()= scheduleDao.getAll()

    fun scheduleForStopName(name: String) = scheduleDao.getByStopName(name)
}
