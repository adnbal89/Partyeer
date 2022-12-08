package com.partyeer.presentation.ui.main.activity

import android.os.Bundle
import com.partyeer.presentation.databinding.ActivitiyClubProfileBinding
import com.partyeer.presentation.ui.main.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClubProfileActivity : BaseActivity() {
    private lateinit var binding: ActivitiyClubProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitiyClubProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}