package com.ubayadev.peptideuts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ubayadev.peptideuts.databinding.FragmentEditHabitBinding
import com.ubayadev.peptideuts.viewmodel.EditHabitViewModel

interface EditHabitListener {
    fun onSaveClick(v: View)
}

class EditHabitFragment : Fragment(), EditHabitListener {

    private lateinit var binding: FragmentEditHabitBinding
    private lateinit var viewModel: EditHabitViewModel
    private val args: EditHabitFragmentArgs by navArgs()

    private val iconLabels = arrayOf("Water", "Walk", "Pray", "Sleep", "Book")
    private val iconKeys = arrayOf("water", "walk", "pray", "sleep", "book")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(EditHabitViewModel::class.java)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            iconLabels
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIcon.adapter = adapter

        binding.listener = this

        viewModel.habitLD.observe(viewLifecycleOwner, Observer { habit ->
            binding.habit = habit
            val idx = iconKeys.indexOf(habit.iconName)
            if (idx >= 0) {
                binding.spinnerIcon.setSelection(idx)
            }
        })

        viewModel.loadHabit(args.habitId)
    }

    override fun onSaveClick(v: View) {
        val habit = binding.habit ?: return

        val goal = binding.txtGoal.text.toString().trim().toIntOrNull()

        if (habit.name.isNullOrEmpty() || habit.description.isNullOrEmpty() ||
            habit.unit.isNullOrEmpty() || goal == null || goal <= 0
        ) {
            Toast.makeText(
                requireContext(),
                "Lengkapi semua field dengan benar",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        habit.goal = goal
        habit.iconName = iconKeys[binding.spinnerIcon.selectedItemPosition]

        viewModel.updateHabit(habit)
        Toast.makeText(requireContext(), "Habit berhasil diperbarui!", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }
}