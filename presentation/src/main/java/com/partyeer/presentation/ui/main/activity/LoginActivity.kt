package com.partyeer.presentation.ui.main.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.partyeer.domain.repository.login.model.UserCredential
import com.partyeer.presentation.databinding.ActivityLoginBinding
import com.partyeer.presentation.ui.main.base.BaseActivity
import com.partyeer.presentation.ui.main.features.login.LoginViewModel
import com.partyeer.presentation.ui.main.util.checker.UserCredentialBlankChecker
import com.partyeer.presentation.ui.main.util.checker.UserCredentialValidChecker
import com.partyeer.presentation.ui.main.util.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private val viewModel: LoginViewModel by viewModels()
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
            if (UserCredentialBlankChecker.check(userCredential))
                viewModel.isUserValid(userCredential)
            else
                Toast.makeText(
                    this@LoginActivity,
                    "Mail or Password cannot be blank",
                    Toast.LENGTH_SHORT
                ).show()
        }

        binding.buttonSignup.setOnClickListener {
            val userMail = binding.textViewUserName.editText?.text.toString()
            val password = binding.textViewPassword.editText?.text.toString()
            val userCredential = UserCredential(userMail, password)
            if (UserCredentialBlankChecker.check(userCredential) && UserCredentialValidChecker.check(
                    userCredential
                )
            )
                viewModel.signupUser(userCredential)
            else
                Toast.makeText(
                    this@LoginActivity,
                    "Mail or Password cannot be blank and must conform to security rules.",
                    Toast.LENGTH_SHORT
                ).show()
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