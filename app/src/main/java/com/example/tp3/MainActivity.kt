package com.example.tp3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.tp3.viewmodels.BusScheduleViewModel
import com.example.tp3.viewmodels.BusScheduleViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: BusScheduleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ViewModel initialization remains the same
        viewModel = ViewModelProvider(
            this,
            BusScheduleViewModelFactory((application as BusScheduleApplication).database.scheduleDao())
        ).get(BusScheduleViewModel::class.java)

        // Add the BusScheduleFragment to the container
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.nav_host_fragment, BusScheduleFragment())
            }
        }
    }

    fun openDetailFragment(stationName: String) {
        // Navigate to the DetailFragment with the station name
        supportFragmentManager.commit {
            replace(R.id.nav_host_fragment, DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(DetailsFragment.EXTRA_STATION_NAME, stationName)
                }
            })
            addToBackStack(null)
        }
    }

    fun openBusScheduleFragment() {
        // Navigate back to the BusScheduleFragment
        supportFragmentManager.popBackStack()
    }
}
