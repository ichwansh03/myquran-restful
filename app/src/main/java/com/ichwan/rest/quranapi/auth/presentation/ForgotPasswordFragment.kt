package com.ichwan.rest.quranapi.auth.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ichwan.rest.quranapi.R
import com.ichwan.rest.quranapi.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)

        binding.apply {
            buttonChange.setOnClickListener {
                findNavController().navigate(R.id.forgot_to_login)
            }
        }

        return binding.root
    }

}