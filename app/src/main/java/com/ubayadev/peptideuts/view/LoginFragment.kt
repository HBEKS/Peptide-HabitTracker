package com.ubayadev.peptideuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ubayadev.peptideuts.R
import com.ubayadev.peptideuts.databinding.FragmentLoginBinding
import com.ubayadev.peptideuts.util.SharedPrefManager
import com.ubayadev.peptideuts.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var prefManager: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefManager = SharedPrefManager(requireContext())
        // Cek Auto-Login
        if (prefManager.isLoggedIn()) {
            val action = LoginFragmentDirections.actionDashboardFragment()
            findNavController().navigate(action)
            return
        }

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            viewModel.loginUser(username, password)
        }
        observeLoginViewModel()
    }

    fun observeLoginViewModel(){
        viewModel.loginSuccess.observe(viewLifecycleOwner, Observer { isSuccess ->
            if (isSuccess) {
                val username = binding.txtUsername.text.toString()
                prefManager.saveSession(username)

                Toast.makeText(requireContext(), "Login Berhasil!", Toast.LENGTH_SHORT).show()
                val action = LoginFragmentDirections.actionDashboardFragment()
                findNavController().navigate(action)
            }
        })
    }
}