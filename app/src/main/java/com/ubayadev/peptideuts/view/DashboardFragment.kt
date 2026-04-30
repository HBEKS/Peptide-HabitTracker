package com.ubayadev.peptideuts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubayadev.peptideuts.databinding.FragmentDashboardBinding
import com.ubayadev.peptideuts.model.Habit
import com.ubayadev.peptideuts.viewmodel.DashboardViewModel

class DashboardFragment : Fragment(), HabitAdapter.HabitClickListener {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: DashboardViewModel
    private val habitAdapter = HabitAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        viewModel.refresh()

        binding.recViewHabit.layoutManager = LinearLayoutManager(context)
        binding.recViewHabit.adapter = habitAdapter

        observeViewModel()

        binding.fabAdd.setOnClickListener {
            val action = DashboardFragmentDirections.actionCreateHabitFragment()
            it.findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }

    fun observeViewModel() {
        viewModel.habitsLD.observe(viewLifecycleOwner, Observer {
            habitAdapter.updateHabitList(it)
            if (it.isEmpty()) {
                binding.txtEmpty.visibility = View.VISIBLE
                binding.recViewHabit.visibility = View.GONE
            } else {
                binding.txtEmpty.visibility = View.GONE
                binding.recViewHabit.visibility = View.VISIBLE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.progressLoad.visibility = View.VISIBLE
                binding.recViewHabit.visibility = View.GONE
            } else {
                binding.progressLoad.visibility = View.GONE
            }
        })
    }

    override fun onPlusClick(habit: Habit) {
        viewModel.updateProgress(habit, +1)
    }

    override fun onMinusClick(habit: Habit) {
        viewModel.updateProgress(habit, -1)
    }
}