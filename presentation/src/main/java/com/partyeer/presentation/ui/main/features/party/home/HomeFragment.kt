package com.partyeer.presentation.ui.main.features.party.home

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
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
import com.partyeer.presentation.ui.main.util.setDivider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    private val pictureRecyclerViewAdapter by lazy {
        PictureRecyclerViewAdapter()
    }

    private val partyListRecyclerViewAdapter by lazy {
        PartyListRecyclerViewAdapter()
    }

    override fun initViews() {
        with(binding.viewPagerPicturesHeadline) {
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
            setDivider(drawableRes = R.drawable.bg_divider, showLastDivider = false)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = partyListRecyclerViewAdapter.apply {
                onItemClick = {
                    val list =
                        it.pictures
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
                    uri = Uri.parse(pictureRecyclerViewAdapter.getItem(binding.viewPagerPicturesHeadline.currentItem)!!.preview)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        val actionCancel = menu.findItem(R.id.action_cancel)
        val actionPublish = menu.findItem(R.id.action_publish)
        actionCancel.isVisible = false
        actionPublish.isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setPictureIndicatorText(position: Int) {
        binding.textViewPictureIndicator.text = getString(
            R.string.picture_indicator,
            position + 1,
            pictureRecyclerViewAdapter.itemCount
        )
    }
}