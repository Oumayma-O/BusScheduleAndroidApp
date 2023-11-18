package com.example.tp3;

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3.database.entities.Schedule

class BusStopAdapter(
    private val onItemClickListener: (Schedule) -> Unit
) : RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {

    private var scheduleList: List<Schedule> = emptyList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stopNameTextView: TextView = itemView.findViewById(R.id.stopNameTextView)
        val arrivalTimeTextView: TextView = itemView.findViewById(R.id.arrivalTimeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bus_stop, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSchedule = scheduleList[position]
        holder.stopNameTextView.text = currentSchedule.stopName
        holder.arrivalTimeTextView.text =
            currentSchedule.arrivalTime.toString()

        holder.itemView.setOnClickListener {
            // Handle item click
            onItemClickListener.invoke(currentSchedule)
            Log.d("BusStopAdapter", "Item clicked: ${currentSchedule.stopName}")

        }
    }


    override fun getItemCount(): Int {
        return scheduleList.size
    }

    // Update the list
    fun updateList(newList: List<Schedule>) {
        scheduleList = newList
        notifyDataSetChanged()
    }

}
