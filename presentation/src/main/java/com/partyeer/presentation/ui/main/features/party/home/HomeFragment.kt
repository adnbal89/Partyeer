package com.partyeer.presentation.ui.main.features.party.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.BottomSheetDialogLayoutBinding
import com.partyeer.presentation.databinding.FragmentHomeBinding
import com.partyeer.presentation.ui.main.base.BaseMvvmFragment
import com.partyeer.presentation.ui.main.features.party.PartyListRecyclerViewAdapter
import com.partyeer.presentation.ui.main.util.setDivider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    /*  private val pictureRecyclerViewAdapter by lazy {
          PictureRecyclerViewAdapter()
      }*/

    private val partyListRecyclerViewAdapter by lazy {
        PartyListRecyclerViewAdapter() { party ->
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            val view = BottomSheetDialogLayoutBinding.inflate(layoutInflater, binding.root, false)
            bottomSheetDialog.setContentView(view.root);

            bottomSheetDialog.show();

            view.textViewApply.setOnClickListener {
                bottomSheetDialog.dismiss()
                //implement party application process.
            }
            view.textViewHide.setOnClickListener {
                bottomSheetDialog.dismiss()
                //implement party application process.
            }
            view.textViewAddFavorite.setOnClickListener {
                bottomSheetDialog.dismiss()
                //implement party application process.
            }

        }
    }

    override fun initViews() {
        /*with(binding.viewPagerPicturesHeadline) {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    setPictureIndicatorText(position)
                }
            })
            adapter = pictureRecyclerViewAdapter.apply {
                onItemClick = {

                }
            }
        }*/

        with(binding.recyclerViewPartyList) {
            setDivider(drawableRes = R.drawable.bg_divider, showLastDivider = false)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = partyListRecyclerViewAdapter.apply {
                onItemClick = {

                    //setPictureIndicatorText(list.size.coerceAtLeast(1))
                }
            }
        }

        /*with(binding.imageViewShare) {
            setOnClickListener {
                IntentUtil.shareImage(
                    activity = requireActivity(),
                    uri = Uri.parse(pictureRecyclerViewAdapter.getItem(binding.viewPagerPicturesHeadline.currentItem)!!.preview)
                )
            }
        }*/
    }

    override fun observeEvents() {
        super.observeEvents()
        //Collect Party
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.partyList.collect { partyList ->
                with(binding) {
                    //pictureRecyclerViewAdapter.setItems(list)
                    partyListRecyclerViewAdapter.setItems(partyList)
                    //setPictureIndicatorText(list.size.coerceAtLeast(1))
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
        /*binding.textViewPictureIndicator.text = getString(
            R.string.picture_indicator,
            position + 1,
            pictureRecyclerViewAdapter.itemCount
        )*/
    }
}