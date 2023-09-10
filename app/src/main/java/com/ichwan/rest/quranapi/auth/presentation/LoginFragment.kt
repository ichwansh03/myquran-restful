package com.ichwan.rest.quranapi.auth.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.ichwan.rest.quranapi.MainActivity
import com.ichwan.rest.quranapi.R
import com.ichwan.rest.quranapi.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()

        binding.apply {
            buttonLogin.setOnClickListener {
                val email = emailInput.text.toString().trim()
                val password = passwordInput.text.toString().trim()

                if (email.isEmpty()) {
                    emailField.error = "Email cannot blank"
                    emailField.requestFocus()
                    return@setOnClickListener
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailField.error = "Email not valid"
                    emailField.requestFocus()
                    return@setOnClickListener
                }
                if (password.isEmpty() || password.length < 8){
                    passwordField.error = "Password must have 8 character or more"
                    passwordField.requestFocus()
                    return@setOnClickListener
                }

                login(email, password)

            }

            buttonRegister.setOnClickListener {
                findNavController().navigate(R.id.login_to_register)
            }

            txForgotPassword.setOnClickListener {
                findNavController().navigate(R.id.login_to_forgot)
            }
        }

        return binding.root
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()){
                if (it.isSuccessful){
                    Intent(requireActivity(), MainActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                } else {
                    Toast.makeText(requireContext(), "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Intent(requireActivity(), MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}