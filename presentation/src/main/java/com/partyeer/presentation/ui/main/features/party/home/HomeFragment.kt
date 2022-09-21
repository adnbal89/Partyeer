package com.partyeer.presentation.ui.main.features.party.home

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.domain.repository.party.model.Picture
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.FragmentHomeBinding
import com.partyeer.presentation.ui.main.base.BaseMvvmFragment
import com.partyeer.presentation.ui.main.features.party.PartyListRecyclerViewAdapter
import com.partyeer.presentation.ui.main.features.party.createparty.PictureRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, HomeViewModel>() {

    private val pictureRecyclerViewAdapter by lazy {
        PictureRecyclerViewAdapter()
    }

    private val partyListRecyclerViewAdapter by lazy {
        PartyListRecyclerViewAdapter()
    }

    override fun initViews() {
        with(binding.viewPagerPictures) {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    setPictureIndicatorText(position)
                }
            })
            adapter = pictureRecyclerViewAdapter.apply {
                onItemClick = {
                    Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show()
                }
            }
        }

        with(binding.recyclerViewPartyList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = partyListRecyclerViewAdapter
        }
    }

    override fun observeEvents() {
        super.observeEvents()
        //Collect Party
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.party.collect {
                with(binding) {
                    val list = listOf<Picture>(Picture(), Picture(), Picture(), Picture())
                    val listParty = listOf<Party>(
                        Party(
                            "1",
                            "https://images.unsplash.com/photo-1661612117616-84b7fcf639d6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTY2MzY5MjQ5Nw&ixlib=rb-1.2.1&q=80&w=200",
                            "First Party",
                            "Techno",
                            123213123.0
                        )
                    )

                    setPictureIndicatorText(list.size.coerceAtLeast(1))
                    pictureRecyclerViewAdapter.setItems(list)
                    partyListRecyclerViewAdapter.setItems(listParty)
                }
            }


        }
    }

    private fun setPictureIndicatorText(position: Int) {
        binding.textViewPictureIndicator.text = getString(
            R.string.picture_indicator,
            position + 1,
            pictureRecyclerViewAdapter.itemCount
        )
    }
}