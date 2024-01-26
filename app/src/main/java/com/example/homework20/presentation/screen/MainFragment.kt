package com.example.homework20.presentation.screen

import android.graphics.Color
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homework20.R
import com.example.homework20.databinding.FragmentMainBinding
import com.example.homework20.presentation.common.BaseFragment
import com.example.homework20.presentation.event.MainEvent
import com.example.homework20.presentation.model.UserPresentation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    override fun setupListeners() {
        btnAddListener()
        btnDeleteListener()
        btnUpdateListener()
    }

    override fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    state.errorMessage?.let {
                        showError(it)
                        viewModel.resetMessages()
                    }
                    state.successMessage?.let {
                        showSuccess(it)
                        viewModel.resetMessages()
                    }
                }
            }
        }
    }


    private fun btnAddListener() {
        binding.btnAddUser.setOnClickListener {
            val user = UserPresentation(
                firstName = binding.etFirstName.text.toString(),
                lastName = binding.etLastName.text.toString(),
                email = binding.etEmail.text.toString(),
                age = binding.etAge.text.toString()
            )
            viewModel.onEvent(MainEvent.AddUser(user))
        }
    }

    private fun btnDeleteListener() {
        binding.btnDeleteUser.setOnClickListener {
            val email = binding.etEmail.text.toString()
            viewModel.onEvent(MainEvent.DeleteUser(UserPresentation(email = email)))
        }
    }

    private fun btnUpdateListener() {
        binding.btnUpdateUser.setOnClickListener {
            val user = UserPresentation(
                firstName = binding.etFirstName.text.toString(),
                lastName = binding.etLastName.text.toString(),
                email = binding.etEmail.text.toString(),
                age = binding.etAge.text.toString()
            )
            viewModel.onEvent(MainEvent.UpdateUser(user))
        }
    }




    private fun showError(message: String) {
        binding.tvResult.text = message
        binding.tvResult.setTextColor(Color.RED)
    }

    private fun showSuccess(message: String) {
        binding.tvResult.text = message
        binding.tvResult.setTextColor(Color.GREEN)
    }



}