package com.partyeer.presentation.ui.main.features.party.createparty

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.ActivityCreatePartyBinding
import com.partyeer.presentation.ui.main.base.BaseMvvmFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePartyFragment : BaseMvvmFragment<ActivityCreatePartyBinding, CreatePartyViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun initViews() {
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create_party, menu)
    }

    override fun observeEvents() {
        super.observeEvents()
        //Collect Party
    }
}