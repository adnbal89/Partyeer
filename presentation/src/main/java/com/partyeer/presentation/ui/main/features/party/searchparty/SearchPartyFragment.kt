package com.partyeer.presentation.ui.main.features.party.searchparty

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.FragmentSearchPartyBinding
import com.partyeer.presentation.ui.main.base.BaseMvvmFragment
import com.partyeer.presentation.ui.main.view.extension.onQueryTextSubmit
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchPartyFragment : BaseMvvmFragment<FragmentSearchPartyBinding, SearchPartyViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun initViews() {
    }

    override fun observeEvents() {
        super.observeEvents()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search_parties, menu)
        menu.setGroupVisible(0, false)

        val searchItem = menu.findItem(R.id.action_search_party)
        val refreshItem = menu.findItem(R.id.action_refresh)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search)

        searchItem.isVisible = true
        refreshItem.isVisible = true

        searchView.onQueryTextSubmit { query ->
            viewModel.onSearchQuerySubmit(query)
            searchView.clearFocus()
        }

        super.onCreateOptionsMenu(menu, inflater)
    }
}