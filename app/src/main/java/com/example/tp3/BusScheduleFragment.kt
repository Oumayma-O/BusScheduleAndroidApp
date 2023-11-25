package com.example.tp3;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3.viewmodels.BusScheduleViewModel
import com.example.tp3.viewmodels.BusScheduleViewModelFactory

class BusScheduleFragment : Fragment() {

    private lateinit var busStopAdapter: BusStopAdapter
    private lateinit var viewModel: BusScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bus_schedule, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        busStopAdapter = BusStopAdapter { schedule ->
            (requireActivity() as MainActivity).openDetailFragment(schedule.stopName)
        }
        recyclerView.adapter = busStopAdapter

        // ViewModel initialization remains the same
        viewModel = ViewModelProvider(
            this,
            BusScheduleViewModelFactory((requireActivity().application as BusScheduleApplication).database.scheduleDao())
        ).get(BusScheduleViewModel::class.java)

        // Observe LiveData in the ViewModel
        viewModel.fullSchedule().observe(viewLifecycleOwner) { schedules ->
            // Update the adapter with the new data
            busStopAdapter.updateList(schedules)
        }

        return view
    }
}
