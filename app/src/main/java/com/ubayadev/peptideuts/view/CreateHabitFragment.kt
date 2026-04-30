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
import androidx.navigation.findNavController
import com.ubayadev.peptideuts.databinding.FragmentCreateHabitBinding
import com.ubayadev.peptideuts.viewmodel.CreateHabitViewModel
import com.ubayadev.peptideuts.viewmodel.DashboardViewModel

class CreateHabitFragment : Fragment() {

    private lateinit var binding: FragmentCreateHabitBinding
    private lateinit var viewModel: CreateHabitViewModel
    private lateinit var dashboardViewModel: DashboardViewModel

    private val iconLabels = arrayOf("Water", "Walk", "Pray", "Sleep", "Book")
    private val iconKeys = arrayOf("water", "walk", "pray", "sleep", "book")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CreateHabitViewModel::class.java)
        dashboardViewModel = ViewModelProvider(requireActivity()).get(DashboardViewModel::class.java)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            iconLabels
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIcon.adapter = adapter

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMsg ->
            if (errorMsg != null) {
                Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.habitCreated.observe(viewLifecycleOwner, Observer { habit ->
            if (habit != null) {
                dashboardViewModel.addHabit(habit)
                Toast.makeText(requireContext(), "Habit berhasil dibuat!", Toast.LENGTH_SHORT).show()
                view.findNavController().popBackStack()
            }
        })

        binding.btnCreateHabit.setOnClickListener {
            val name = binding.txtName.text.toString().trim()
            val desc = binding.txtDescription.text.toString().trim()
            val goal = binding.txtGoal.text.toString().trim()
            val unit = binding.txtUnit.text.toString().trim()
            val selectedIconKey = iconKeys[binding.spinnerIcon.selectedItemPosition]

            viewModel.createHabit(name, desc, goal, unit, selectedIconKey)
        }
    }
}