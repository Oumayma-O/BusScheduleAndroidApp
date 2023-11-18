package com.example.tp3.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tp3.database.entities.Schedule
import com.example.tp3.database.entities.ScheduleDao

class BusScheduleViewModel(private val scheduleDao: ScheduleDao) : ViewModel() {

    fun fullSchedule(): LiveData<List<Schedule>> {
        return scheduleDao.getAll()
    }

    fun scheduleForStopName(stopName: String): LiveData<List<Schedule>> {
        return scheduleDao.getByStopName(stopName)
    }

}
