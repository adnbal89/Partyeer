package com.partyeer.presentation.ui.main.features.home

import androidx.lifecycle.lifecycleScope
import com.partyeer.presentation.databinding.FragmentHomeBinding
import com.partyeer.presentation.ui.main.base.BaseMvvmFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun initViews() {
    }

    override fun observeEvents() {
        super.observeEvents()

        //Collect Party
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.party.collect {
                with(binding) {
                    textView4.text = it.id
                }
            }
        }
    }
}