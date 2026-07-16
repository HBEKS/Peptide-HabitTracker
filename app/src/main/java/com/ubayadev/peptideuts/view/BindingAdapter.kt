package com.ubayadev.peptideuts.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ubayadev.peptideuts.R
import com.ubayadev.peptideuts.model.Habit

@BindingAdapter("habitIcon")
fun setHabitIcon(view: ImageView, iconName: String?) {
    val iconResId = when (iconName) {
        "water" -> R.drawable.ic_water
        "walk" -> R.drawable.ic_walk
        "pray" -> R.drawable.ic_pray
        "sleep" -> R.drawable.ic_sleep
        "book" -> R.drawable.ic_book
        else -> R.drawable.ic_water
    }
    view.setImageResource(iconResId)
}

@BindingAdapter("progressData")
fun setProgressData(bar: ProgressBar, habit: Habit?) {
    if (habit == null) return
    val goal = habit.goal ?: 1
    val progress = habit.progress ?: 0
    bar.max = goal
    bar.progress = progress
}

@BindingAdapter("progressText")
fun setProgressText(view: TextView, habit: Habit?) {
    if (habit == null) return
    val progress = habit.progress ?: 0
    val goal = habit.goal ?: 1
    val unit = habit.unit ?: ""
    val percent = if (goal > 0) progress * 100 / goal else 0
    val baseText = if (unit.isNotEmpty()) "$progress / $goal $unit" else "$progress / $goal"
    view.text = "$baseText ($percent%)"
}

@BindingAdapter("statusText")
fun setStatusText(view: TextView, habit: Habit?) {
    if (habit == null) return
    val progress = habit.progress ?: 0
    val goal = habit.goal ?: 1
    view.text = if (progress >= goal) "Completed" else "In Progress"
}

@BindingAdapter("enablePlus")
fun setEnablePlus(view: View, habit: Habit?) {
    if (habit == null) return
    val progress = habit.progress ?: 0
    val goal = habit.goal ?: 1
    view.isEnabled = progress < goal
}

@BindingAdapter("enableMinus")
fun setEnableMinus(view: View, habit: Habit?) {
    if (habit == null) return
    val progress = habit.progress ?: 0
    view.isEnabled = progress > 0
}

@BindingAdapter("progressColor")
fun setProgressColor(bar: ProgressBar, habit: Habit?) {
    if (habit == null) return
    val goal = habit.goal ?: 1
    val progress = habit.progress ?: 0
    val percent = if (goal > 0) progress * 100 / goal else 0
    val color = when {
        percent >= 100 -> Color.parseColor("#4CAF50")
        percent >= 50 -> Color.parseColor("#FFC107")
        else -> Color.parseColor("#F44336")
    }
    bar.progressDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
}

@BindingAdapter("statusBackground")
fun setStatusBackground(view: TextView, habit: Habit?) {
    if (habit == null) return
    val progress = habit.progress ?: 0
    val goal = habit.goal ?: 1
    val color = if (progress >= goal) Color.parseColor("#4CAF50") else Color.parseColor("#9E9E9E")
    view.setBackgroundColor(color)
}

@BindingAdapter("iconTint")
fun setIconTint(view: ImageView, habit: Habit?) {
    if (habit == null) return
    val progress = habit.progress ?: 0
    val goal = habit.goal ?: 1
    val color = if (progress >= goal) Color.parseColor("#4CAF50") else Color.parseColor("#5000FF")
    view.setColorFilter(color, PorterDuff.Mode.SRC_IN)
}

@BindingAdapter("visibleIf")
fun setVisibleIf(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("percentText")
fun setPercentText(view: TextView, habit: Habit?) {
    if (habit == null) return
    val progress = habit.progress ?: 0
    val goal = habit.goal ?: 1
    val percent = if (goal > 0) progress * 100 / goal else 0
    view.text = "$percent%"
}

@BindingAdapter("statusBadgeBg")
fun setStatusBadgeBg(view: TextView, habit: Habit?) {
    if (habit == null) return
    val progress = habit.progress ?: 0
    val goal = habit.goal ?: 1
    val resId = if (progress >= goal) R.drawable.bg_badge_completed else R.drawable.bg_badge_progress
    view.setBackgroundResource(resId)
}