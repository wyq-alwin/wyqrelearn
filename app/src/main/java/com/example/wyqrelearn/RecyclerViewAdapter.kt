package com.example.wyqrelearn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_card_main, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        Toast.makeText(context, "xyexye", Toast.LENGTH_LONG).show()
    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}