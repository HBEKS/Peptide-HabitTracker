package com.ubayadev.peptideuts.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubayadev.peptideuts.databinding.HabitListItemBinding
import com.ubayadev.peptideuts.model.Habit

class HabitAdapter(
    val habitList: ArrayList<Habit>,
    val listener: HabitClickListener
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    interface HabitClickListener {
        fun onPlusClick(habit: Habit)
        fun onMinusClick(habit: Habit)
        fun onTitleClick(habit: Habit)
    }

    class HabitViewHolder(var binding: HabitListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = HabitListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]
        holder.binding.habit = habit
        holder.binding.listener = listener
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    fun updateHabitList(newList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newList)
        notifyDataSetChanged()
    }
}