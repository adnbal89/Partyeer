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

        auth = Firebase.auth

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

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            println("Auth present.")
        }
    }

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = auth.currentUser
                sendEmailVerification(user!!)
                //navigator.toMainActivity().clearBackStack().navigate()
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(
                    baseContext, "Authentication failed.", Toast.LENGTH_SHORT
                ).show()
                updateUI(null)
            }
        }
        // [END create_user_with_email]
    }

    private fun sendEmailVerification(user: FirebaseUser) {
        // [START send_email_verification]
        user.sendEmailVerification().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    baseContext, "Email Successfully sent!!.", Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    baseContext, "Email failed.", Toast.LENGTH_SHORT
                ).show()
            }
        }
        // [END send_email_verification]
    }

    private fun updateUI(user: FirebaseUser?) {
        binding.textViewPassword.editText?.setText("")
    }

    private fun reload() {

    }

    companion object {
        private const val TAG = "LoginActivity"
    }

}