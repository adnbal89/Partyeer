package com.partyeer.presentation.ui.main.activity

import android.os.Bundle
import com.partyeer.presentation.databinding.ActivitySignupBinding
import com.partyeer.presentation.ui.main.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}