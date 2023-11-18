package com.example.tp3;
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var detailsAdapter: DetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val recyclerView: RecyclerView = findViewById(R.id.detailsRecyclerView)
        val toolBarTitle: TextView = findViewById(R.id.toolbarTitle2)
        val backArrow: ImageView = findViewById(R.id.backArrow)

        val stationName = intent.getStringExtra(EXTRA_STATION_NAME) ?: ""

        toolBarTitle.text = stationName

        GlobalScope.launch(Dispatchers.IO) {
            val schedules = (application as BusScheduleApplication)
                .database.scheduleDao()
                .getByStopName(stationName)

            launch(Dispatchers.Main) {
                recyclerView.layoutManager = LinearLayoutManager(this@DetailActivity)
                detailsAdapter = DetailsAdapter(schedules)
                recyclerView.adapter = detailsAdapter
            }
        }

        backArrow.setOnClickListener {
            startActivity(Intent(this@DetailActivity, MainActivity::class.java))
            finish()
        }
    }

    companion object {
        const val EXTRA_STATION_NAME = "extra_station_name"
    }
}
