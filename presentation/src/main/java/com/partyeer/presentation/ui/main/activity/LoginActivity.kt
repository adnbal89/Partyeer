package com.partyeer.presentation.ui.main.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.partyeer.domain.repository.login.model.UserCredential
import com.partyeer.presentation.databinding.ActivityLoginBinding
import com.partyeer.presentation.ui.main.base.BaseActivity
import com.partyeer.presentation.ui.main.features.login.LoginViewModel
import com.partyeer.presentation.ui.main.util.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding


    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val userMail = binding.textViewUserName.editText?.text.toString()
            val password = binding.textViewPassword.editText?.text.toString()
            val userCredential = UserCredential(userMail, password)
            if (userMail.isNullOrBlank().not() && password.isNullOrBlank().not())
                viewModel.isUserValid(userCredential)
        }

        binding.buttonSignup.setOnClickListener {
            val userMail = binding.textViewUserName.editText?.text.toString()
            val password = binding.textViewPassword.editText?.text.toString()
            val userCredential = UserCredential(userMail, password)
            viewModel.signupUser(userCredential)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect {
                    when (it) {
                        is LoginViewModel.LoginStatus.Success -> navigator.toMainActivity(it.firebaseUser)
                            .clearBackStack().navigate()
                        LoginViewModel.LoginStatus.Error -> Toast.makeText(
                            this@LoginActivity,
                            "Login Failed!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

    }

    companion object {
        private const val TAG = "LoginActivity"
    }

}