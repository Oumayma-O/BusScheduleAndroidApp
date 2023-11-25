package com.example.tp3
// DetailsFragment.kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3.viewmodels.BusScheduleViewModel
import com.example.tp3.viewmodels.BusScheduleViewModelFactory

class DetailsFragment : Fragment() {

    private lateinit var detailsAdapter: DetailsAdapter
    private lateinit var viewModel: BusScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.detailsRecyclerView)
        val toolBarTitle: TextView = view.findViewById(R.id.toolbarTitle2)
        val backArrow: ImageView = view.findViewById(R.id.backArrow)

        // Extract station name from arguments
        val stationName = arguments?.getString(EXTRA_STATION_NAME) ?: ""

        toolBarTitle.text = stationName

        viewModel = ViewModelProvider(
            this,
            BusScheduleViewModelFactory((requireActivity().application as BusScheduleApplication).database.scheduleDao())
        ).get(BusScheduleViewModel::class.java)

        viewModel.scheduleForStopName(stationName).observe(viewLifecycleOwner) { schedules ->
            detailsAdapter.submitList(schedules)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        detailsAdapter = DetailsAdapter()
        recyclerView.adapter = detailsAdapter

        backArrow.setOnClickListener {
            (requireActivity() as MainActivity).openBusScheduleFragment()
        }

        return view
    }

    companion object {
        const val EXTRA_STATION_NAME = "extra_station_name"
    }
}
