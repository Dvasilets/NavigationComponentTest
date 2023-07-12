package com.vasilets.navigationcomponenttest.ui.dashboard.sampleRv

import androidx.recyclerview.widget.RecyclerView
import com.vasilets.navigationcomponenttest.databinding.ItemSampleBinding

class SampleViewHolder(val binding: ItemSampleBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.sampleItem.text = item
    }
}