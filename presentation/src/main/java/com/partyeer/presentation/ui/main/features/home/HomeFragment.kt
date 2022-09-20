package com.partyeer.presentation.ui.main.features.home

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.partyeer.domain.repository.party.model.Picture
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.FragmentHomeBinding
import com.partyeer.presentation.ui.main.base.BaseMvvmFragment
import com.partyeer.presentation.ui.main.features.party.createparty.PictureRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, HomeViewModel>() {

    private val pictureRecyclerViewAdapter by lazy {
        PictureRecyclerViewAdapter()
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
    }

    override fun observeEvents() {
        super.observeEvents()

        //Collect Party
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.party.collect {
                with(binding) {
                    val list = listOf<Picture>(Picture(),Picture(),Picture(),Picture())
                    setPictureIndicatorText(list.size.coerceAtLeast(1))
                    pictureRecyclerViewAdapter.setItems(list)
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