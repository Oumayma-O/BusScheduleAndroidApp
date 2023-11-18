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

        // Initialize ViewModel using ViewModelProvider and ViewModelFactory
        viewModel = ViewModelProvider(
            this,
            BusScheduleViewModelFactory((application as BusScheduleApplication).database.scheduleDao())
        ).get(BusScheduleViewModel::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // Configure RecyclerView and Adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        busStopAdapter = BusStopAdapter { schedule ->
            // Start DetailsActivity on item click
            startDetailsActivity(schedule.stopName)
        }
        recyclerView.adapter = busStopAdapter

        // Use coroutines to fetch data from the database in a background thread
        lifecycleScope.launch(Dispatchers.IO) {
            // Get the list of schedules from the ViewModel
            val schedules = viewModel.fullSchedule()

            // Switch to the main thread to update the UI
            launch(Dispatchers.Main) {
                // Update the adapter with the fetched data
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
