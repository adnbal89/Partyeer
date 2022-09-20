package com.partyeer.presentation.ui.main.features.party.createparty

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.FragmentCreatePartyBinding
import com.partyeer.presentation.ui.main.base.BaseMvvmFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        //Collect Party
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.party.collect {
                with(binding) {
                    textViewPartyTitle.editText?.setText(it.id)
                }
            }
        }
    }
}