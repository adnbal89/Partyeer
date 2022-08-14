package com.partyeer.presentation.ui.main.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.FragmentLoginBinding
import com.partyeer.presentation.ui.main.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun layoutId() = R.layout.fragment_login

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun initViews() {
        binding.buttonSubmit.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_FirstFragment)
        }
    }

}