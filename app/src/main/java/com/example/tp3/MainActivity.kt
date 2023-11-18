package com.example.tp3;

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3.database.AppDatabase
import com.example.tp3.viewmodels.BusScheduleViewModel
import com.example.tp3.viewmodels.BusScheduleViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var busStopAdapter: BusStopAdapter
    private lateinit var viewModel: BusScheduleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appDatabase: AppDatabase by lazy {
            AppDatabase.getDatabase(this)
        }

        viewModel = ViewModelProvider(
            this,
            BusScheduleViewModelFactory((application as BusScheduleApplication).database.scheduleDao())
        ).get(BusScheduleViewModel::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        busStopAdapter = BusStopAdapter { schedule ->
            startDetailsActivity(schedule.stopName)
        }
        recyclerView.adapter = busStopAdapter

        lifecycleScope.launch(Dispatchers.IO) {
            val schedules = viewModel.fullSchedule()

            launch(Dispatchers.Main) {
                busStopAdapter.updateList(schedules)
            }
        }
    }

    private fun startDetailsActivity(stationName: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_STATION_NAME, stationName)
        startActivity(intent)
    }
}
