package com.example.tp3

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3.viewmodels.BusScheduleViewModel
import com.example.tp3.viewmodels.BusScheduleViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var detailsAdapter: DetailsAdapter
    private lateinit var viewModel: BusScheduleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val recyclerView: RecyclerView = findViewById(R.id.detailsRecyclerView)
        val toolBarTitle: TextView = findViewById(R.id.toolbarTitle2)
        val backArrow: ImageView = findViewById(R.id.backArrow)

        val stationName = intent.getStringExtra(EXTRA_STATION_NAME) ?: ""

        toolBarTitle.text = stationName

        viewModel = ViewModelProvider(
            this,
            BusScheduleViewModelFactory((application as BusScheduleApplication).database.scheduleDao())
        ).get(BusScheduleViewModel::class.java)

        viewModel.scheduleForStopName(stationName).observe(this) { schedules ->
            detailsAdapter.submitList(schedules)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        detailsAdapter = DetailsAdapter()
        recyclerView.adapter = detailsAdapter

        backArrow.setOnClickListener {
            startActivity(Intent(this@DetailActivity, MainActivity::class.java))
            finish()
        }
    }

    companion object {
        const val EXTRA_STATION_NAME = "extra_station_name"
    }
}
