package com.partyeer.presentation.ui.main.features.party.home

import android.content.Intent
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
import com.partyeer.presentation.ui.main.activity.PartyMapsActivity
import com.partyeer.presentation.ui.main.base.BaseMvvmFragment
import com.partyeer.presentation.ui.main.features.party.PartyListRecyclerViewAdapter
import com.partyeer.presentation.ui.main.features.party.googlemaps.PartyToPartyMapItemMapper
import com.partyeer.presentation.ui.main.features.party.googlemaps.model.PartyMapItem
import com.partyeer.presentation.ui.main.util.setDivider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, HomeViewModel>() {
    private lateinit var partyArrayList: ArrayList<PartyMapItem>
    private val partymapper: PartyToPartyMapItemMapper = PartyToPartyMapItemMapper()


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

            //TODO: Refactor
            if (party?.appliedUserIdList?.containsKey("adnbal89") == false) {
                view.textViewApply.visibility = View.VISIBLE
            } else {
                //TODO : implement application cancellation
                view.textViewApply.visibility = View.GONE
            }

            view.textViewApply.setOnClickListener {
                viewModel.applyToParty(party?.id)
                bottomSheetDialog.dismiss()
            }
            view.textViewHide.setOnClickListener {
                bottomSheetDialog.dismiss()
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
        partyArrayList = ArrayList<PartyMapItem>()
        //Collect Party
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.partyList.collect { partyList ->
                with(binding) {
                    //pictureRecyclerViewAdapter.setItems(list)
                    partyListRecyclerViewAdapter.setItems(partyList)
                    //setPictureIndicatorText(list.size.coerceAtLeast(1))
                }
                partyList.forEach {
                    //map [Party] to [PartyMapItem] while adding to arraylist
                    partyArrayList.add(partymapper.map(it))
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val actionCancel = menu.findItem(R.id.action_cancel)
        val actionPublish = menu.findItem(R.id.action_publish)
        actionCancel.isVisible = false
        actionPublish.isVisible = false

        //open map activity and send party list
        val actionMap = menu.findItem(R.id.action_open_map_activity)
        actionMap.setOnMenuItemClickListener {
            val intent = Intent(activity, PartyMapsActivity::class.java)
            intent.putExtra("partyList", partyArrayList)
            activity!!.startActivity(intent)
            true
        }

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