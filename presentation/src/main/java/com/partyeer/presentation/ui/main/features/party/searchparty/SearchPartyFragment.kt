package com.partyeer.presentation.ui.main.features.party.searchparty

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.FragmentSearchPartyBinding
import com.partyeer.presentation.ui.main.base.BaseMvvmFragment
import com.partyeer.presentation.ui.main.util.setDivider
import com.partyeer.presentation.ui.main.view.extension.onQueryTextSubmit
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchPartyFragment : BaseMvvmFragment<FragmentSearchPartyBinding, SearchPartyViewModel>() {
    private lateinit var partyArrayList: ArrayList<Party>

    private val searchPartyListRecyclerViewAdapter by lazy {
        SearchPartyRecyclerViewAdapter() { party ->
            Toast.makeText(requireContext(), "Recyc item clicked", Toast.LENGTH_LONG).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun initViews() {
        with(binding.recyclerViewPopularParties) {
            setDivider(drawableRes = R.drawable.bg_divider, showLastDivider = false)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = searchPartyListRecyclerViewAdapter.apply {
                onItemClick = {

                }
            }
        }

        with(binding.recyclerViewHappyHours) {
            setDivider(drawableRes = R.drawable.bg_divider, showLastDivider = false)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = searchPartyListRecyclerViewAdapter.apply {
                onItemClick = {

                }
            }
        }

        with(binding.recyclerViewDiscountParties) {
            setDivider(drawableRes = R.drawable.bg_divider, showLastDivider = false)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = searchPartyListRecyclerViewAdapter.apply {
                onItemClick = {

                }
            }
        }

        with(binding.recyclerViewEliteParties) {
            setDivider(drawableRes = R.drawable.bg_divider, showLastDivider = false)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = searchPartyListRecyclerViewAdapter.apply {
                onItemClick = {

                }
            }
        }
    }

    override fun observeEvents() {
        super.observeEvents()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.partyList.collect { partyList ->
                    partyArrayList = ArrayList<Party>()
                    with(binding) {
                        searchPartyListRecyclerViewAdapter.setItems(partyList)
                    }
                }
            }
        }
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