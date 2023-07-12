package com.vasilets.navigationcomponenttest.ui.dashboard.sampleRv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vasilets.navigationcomponenttest.databinding.ItemSampleBinding

class SampleAdapter(val items: List<String>,
) : RecyclerView.Adapter<SampleViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder(
            ItemSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(viewHolder: SampleViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }
}