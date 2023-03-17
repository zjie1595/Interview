package com.zj.interview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zj.interview.databinding.ItemInterviewBinding
import com.zj.interview.model.Interview

object InterviewComparator : DiffUtil.ItemCallback<Interview>() {
    override fun areItemsTheSame(oldItem: Interview, newItem: Interview): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Interview, newItem: Interview): Boolean {
        return oldItem == newItem
    }
}

class InterviewAdapter :
    PagingDataAdapter<Interview, InterviewAdapter.InterviewViewHolder>(InterviewComparator) {

    override fun onBindViewHolder(holder: InterviewViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterviewViewHolder {
        val binding =
            ItemInterviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InterviewViewHolder(binding)
    }

    inner class InterviewViewHolder(
        private val binding: ItemInterviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(interview: Interview) {
            binding.m = interview
            binding.executePendingBindings()
        }
    }
}