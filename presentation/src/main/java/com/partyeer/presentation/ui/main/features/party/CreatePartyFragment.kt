package com.partyeer.presentation.ui.main.features.party

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.FragmentCreatePartyBinding
import com.partyeer.presentation.ui.main.base.BaseFragment
import com.partyeer.presentation.ui.main.base.BaseMvvmFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePartyFragment : BaseMvvmFragment<FragmentCreatePartyBinding, CreatePartyViewModel>() {

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
    }
}