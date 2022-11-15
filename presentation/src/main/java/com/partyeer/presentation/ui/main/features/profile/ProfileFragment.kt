package com.partyeer.presentation.ui.main.features.profile

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.FragmentProfileBinding
import com.partyeer.presentation.ui.main.base.BaseMvvmFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseMvvmFragment<FragmentProfileBinding, ProfileViewModel>() {
    override fun initViews() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search_parties, menu)
        menu.setGroupVisible(0, false)
    }

}