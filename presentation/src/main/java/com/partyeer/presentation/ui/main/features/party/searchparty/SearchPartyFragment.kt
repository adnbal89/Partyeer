package com.partyeer.presentation.ui.main.features.party.searchparty

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.domain.repository.party.model.Tag
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.FragmentSearchPartyBinding
import com.partyeer.presentation.ui.main.base.BaseMvvmFragment
import com.partyeer.presentation.ui.main.util.navigation.Navigator
import com.partyeer.presentation.ui.main.util.setDivider
import com.partyeer.presentation.ui.main.view.extension.onQueryTextSubmit
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchPartyFragment : BaseMvvmFragment<FragmentSearchPartyBinding, SearchPartyViewModel>() {
    private lateinit var partyArrayList: ArrayList<Party>

    @Inject
    lateinit var navigator: Navigator

    private val searchPartyListPopularPartiesRecyclerViewAdapter by lazy {
        BasicPartyRecyclerViewAdapter() { party ->
            navigator.toPartyDetailsActivity(party)
                .navigate()
        }
    }
    private val searchPartyListHappyHourPartiesRecyclerViewAdapter by lazy {
        BasicPartyRecyclerViewAdapter() { party ->
            navigator.toPartyDetailsActivity(party)
                .navigate()
        }
    }
    private val searchPartyListDiscountPartiesRecyclerViewAdapter by lazy {
        BasicPartyRecyclerViewAdapter() { party ->
            navigator.toPartyDetailsActivity(party)
                .navigate()
        }
    }
    private val searchPartyListElitePartiesRecyclerViewAdapter by lazy {
        BasicPartyRecyclerViewAdapter() { party ->
            navigator.toPartyDetailsActivity(party)
                .navigate()
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
            adapter = searchPartyListPopularPartiesRecyclerViewAdapter
        }

        with(binding.recyclerViewHappyHours) {
            setDivider(drawableRes = R.drawable.bg_divider, showLastDivider = false)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = searchPartyListHappyHourPartiesRecyclerViewAdapter

        }

        with(binding.recyclerViewDiscountParties) {
            setDivider(drawableRes = R.drawable.bg_divider, showLastDivider = false)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = searchPartyListDiscountPartiesRecyclerViewAdapter
        }

        with(binding.recyclerViewEliteParties) {
            setDivider(drawableRes = R.drawable.bg_divider, showLastDivider = false)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = searchPartyListElitePartiesRecyclerViewAdapter
        }

        with(binding) {
            //TODO : fix and make dynamic
            imageViewPopularPartiesContinuous.setOnClickListener {
                navigator.toSameTaggedPartiesActivity("Beer").navigate()
            }
            imageViewPopularPartiesContinuous2.setOnClickListener {
                navigator.toSameTaggedPartiesActivity("Beer").navigate()
            }
            imageViewPopularPartiesContinuous3.setOnClickListener {
                navigator.toSameTaggedPartiesActivity("Beer").navigate()
            }
            imageViewPopularPartiesContinuous4.setOnClickListener {
                navigator.toSameTaggedPartiesActivity("Beer").navigate()
            }
        }
    }

    override fun observeEvents() {
        super.observeEvents()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tagList.collect {
                    if (it.size >= 4) {
                        createSearchPartyTitles(binding, it)
                        searchPartyListPopularPartiesRecyclerViewAdapter.setItems(it.get(0).parties.values)
                        searchPartyListHappyHourPartiesRecyclerViewAdapter.setItems(it.get(1).parties.values)
                        searchPartyListDiscountPartiesRecyclerViewAdapter.setItems(it.get(2).parties.values)
                        searchPartyListElitePartiesRecyclerViewAdapter.setItems(it.get(3).parties.values)
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

    private fun createSearchPartyTitles(binding: FragmentSearchPartyBinding, tagList: List<Tag>) {
        binding.textViewTitlePopularParties.text = tagList[0].title
        binding.textViewTitleHappyHours.text = tagList[1].title
        binding.textViewTitleDiscountParties.text = tagList[2].title
        binding.textViewTitleEliteParties.text = tagList[3].title

    }
}