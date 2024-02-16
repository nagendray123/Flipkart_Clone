package com.example.project.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.project.R
import com.example.project.databinding.FragmentLoginBinding
import com.example.project.repository.UserRepository
import com.example.project.room.UserDatabase
import com.example.project.viewmodel.UserModelFactory
import com.example.project.viewmodel.UserViewModel

class LoginFragment : Fragment() {

    // ViewBinding
    private lateinit var binding: FragmentLoginBinding
    // Initialize ViewModel
    private val viewModel: UserViewModel by viewModels {
        UserModelFactory(UserRepository(UserDatabase.getDatabase(requireContext()).userDao()))
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login, container, false)
         binding = FragmentLoginBinding.inflate(inflater, container, false)
            return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // if user is not register and go the registration page
        binding.registerRedirect.setOnClickListener {
           findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        //Set UI elements
        binding.loginButton.setOnClickListener{
            val username = binding.loginName.text.toString()
            val password = binding.loginPassword.text.toString()

            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(requireContext(), "Enter Username and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Perform Login
            viewModel.loginUser(username, password)

            viewModel.loggedInUser.observe(viewLifecycleOwner) { user ->
                if (user != null) {
                    findNavController().navigate(R.id.action_loginFragment_to_productFragment)
                    Toast.makeText(requireContext(), "User Login is Successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "User is not Register", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}