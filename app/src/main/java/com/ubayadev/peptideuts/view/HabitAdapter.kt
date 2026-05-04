package com.ubayadev.peptideuts.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubayadev.peptideuts.R
import com.ubayadev.peptideuts.databinding.HabitListItemBinding
import com.ubayadev.peptideuts.model.Habit

class HabitAdapter(
    val habitList: ArrayList<Habit>,
    val listener: HabitClickListener
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    interface HabitClickListener {
        fun onPlusClick(habit: Habit)
        fun onMinusClick(habit: Habit)
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

        holder.binding.txtHabitName.text = habit.name
        holder.binding.txtHabitDesc.text = habit.description

        val progress = habit.progress ?: 0
        val goal = habit.goal ?: 1
        val unit = habit.unit ?: ""

        val percent = if (goal > 0) (progress * 100 / goal) else 0
        val baseText = if (unit.isNotEmpty()) "$progress / $goal $unit" else "$progress / $goal"
        holder.binding.txtProgress.text = "$baseText ($percent%)"

        holder.binding.progressBar.max = goal
        holder.binding.progressBar.progress = progress

        val isCompleted = progress >= goal
        if (isCompleted) {
            holder.binding.txtStatus.text = "Completed"
            holder.binding.txtStatus.setBackgroundResource(R.drawable.bg_status_completed)
        } else {
            holder.binding.txtStatus.text = "In Progress"
            holder.binding.txtStatus.setBackgroundResource(R.drawable.bg_status_in_progress)
        }

        holder.binding.btnPlus.isEnabled = !isCompleted
        holder.binding.btnMinus.isEnabled = progress > 0

        val iconResId = when (habit.iconName) {
            "water" -> R.drawable.ic_water
            "walk" -> R.drawable.ic_walk
            "pray" -> R.drawable.ic_pray
            "sleep" -> R.drawable.ic_sleep
            "book" -> R.drawable.ic_book
            else -> R.drawable.ic_water
        }
        holder.binding.imgIcon.setImageResource(iconResId)

        holder.binding.btnPlus.setOnClickListener {
            listener.onPlusClick(habit)
        }
        holder.binding.btnMinus.setOnClickListener {
            listener.onMinusClick(habit)
        }
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