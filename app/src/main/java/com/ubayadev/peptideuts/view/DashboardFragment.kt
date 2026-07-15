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
import com.google.android.material.snackbar.Snackbar
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

        viewModel = ViewModelProvider(requireActivity()).get(DashboardViewModel::class.java)

        binding.recViewHabit.layoutManager = LinearLayoutManager(context)
        binding.recViewHabit.adapter = habitAdapter

        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }

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
                binding.layoutEmpty.visibility = View.VISIBLE
                binding.recViewHabit.visibility = View.GONE
            } else {
                binding.layoutEmpty.visibility = View.GONE
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

        viewModel.completedEvent.observe(viewLifecycleOwner, Observer { habitName ->
            if (habitName != null) {
                Snackbar.make(
                    binding.root,
                    "Selamat! Habit \"$habitName\" sudah tuntas hari ini.",
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.clearCompletedEvent()
            }
        })
    }

    override fun onPlusClick(habit: Habit) {
        viewModel.updateProgress(habit, +1)
    }

    override fun onTitleClick(habit: Habit) {
        val action = DashboardFragmentDirections.actionEditHabitFragment(habit.id)
        requireView().findNavController().navigate(action)
    }

    override fun onMinusClick(habit: Habit) {
        viewModel.updateProgress(habit, -1)
    }
}