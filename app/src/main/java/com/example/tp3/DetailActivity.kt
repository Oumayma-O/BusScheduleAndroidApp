package com.example.tp3

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val stationName = intent.getStringExtra(EXTRA_STATION_NAME) ?: ""

    }

    companion object {
        const val EXTRA_STATION_NAME = "extra_station_name"
    }
}
