package com.example.project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.project.R
import com.example.project.databinding.FragmentRegistrationBinding
import com.example.project.repository.UserRepository
import com.example.project.room.UserDatabase
import com.example.project.viewmodel.UserModelFactory
import com.example.project.viewmodel.UserViewModel

class RegistrationFragment : Fragment() {
    // ViewBinding
    private lateinit var binding: FragmentRegistrationBinding
    // initialize ViewModel
    private val viewModel: UserViewModel by viewModels {
        UserModelFactory(UserRepository(UserDatabase.getDatabase(requireContext()). userDao()))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_registration, container, false)
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            // set Up UI element
        binding.registerButton.setOnClickListener {
                val username = binding.registerUsername.text.toString()
                val password = binding.registerPassword.text.toString()

            if(isInputValid(username, password)){
                    // Perform registration
                    viewModel.registerUser(username, password)
                    findNavController().navigate(R.id.action_registrationFragment_to_productFragment)
            }
        }
    }
    private fun isInputValid(username: String, password: String): Boolean{
      if(username.isEmpty()){
          binding.registerUsername.error = "Please Enter Your Username"
          return false
      }
        if (password.isEmpty()){
            binding.registerPassword.error = "Please Enter Your Password"
            return false
        }
        return true
    }
}