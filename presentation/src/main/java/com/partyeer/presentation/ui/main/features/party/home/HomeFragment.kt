package com.partyeer.presentation.ui.main.features.party.home

import android.net.Uri
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.partyeer.domain.repository.party.model.Picture
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.FragmentHomeBinding
import com.partyeer.presentation.ui.main.base.BaseMvvmFragment
import com.partyeer.presentation.ui.main.features.party.PartyListRecyclerViewAdapter
import com.partyeer.presentation.ui.main.features.party.createparty.PictureRecyclerViewAdapter
import com.partyeer.presentation.ui.main.util.IntentUtil
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

                }
            }
        }

        with(binding.recyclerViewPartyList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = partyListRecyclerViewAdapter.apply {
                onItemClick = {
                    val list =
                        listOf(Picture(preview = Uri.parse("https://images.unsplash.com/5/unsplash-kitsune-4.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjEyMDd9&s=fb86e2e09fceac9b363af536b93a1275")))

                    pictureRecyclerViewAdapter.setItems(
                        list
                    )
                    setPictureIndicatorText(list.size.coerceAtLeast(1))
                }
            }
        }

        with(binding.imageViewShare) {
            setOnClickListener {
                IntentUtil.shareImage(
                    activity = requireActivity(),
                    uri = pictureRecyclerViewAdapter.getItem(binding.viewPagerPictures.currentItem)!!.preview
                )
            }
        }
    }

    override fun observeEvents() {
        super.observeEvents()
        //Collect Party
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.partyList.collect { partyList ->
                with(binding) {
                    val list = listOf<Picture>(Picture(), Picture(), Picture(), Picture())

                    pictureRecyclerViewAdapter.setItems(list)
                    partyListRecyclerViewAdapter.setItems(partyList)
                    setPictureIndicatorText(list.size.coerceAtLeast(1))
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