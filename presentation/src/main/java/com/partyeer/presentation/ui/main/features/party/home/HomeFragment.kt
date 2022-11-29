package com.partyeer.presentation.ui.main.features.party.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.BottomSheetDialogLayoutBinding
import com.partyeer.presentation.databinding.FragmentHomeBinding
import com.partyeer.presentation.ui.main.activity.PartyDetailActivity
import com.partyeer.presentation.ui.main.activity.PartyMapsActivity
import com.partyeer.presentation.ui.main.base.BaseMvvmFragment
import com.partyeer.presentation.ui.main.extension.showSnackbar
import com.partyeer.presentation.ui.main.features.party.PartyListRecyclerViewAdapter
import com.partyeer.presentation.ui.main.features.party.googlemaps.PartyToPartyMapItemMapper
import com.partyeer.presentation.ui.main.features.party.googlemaps.model.PartyMapItem
import com.partyeer.presentation.ui.main.util.setDivider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, HomeViewModel>() {
    private lateinit var partyArrayList: ArrayList<PartyMapItem>
    private val partyMapper: PartyToPartyMapItemMapper = PartyToPartyMapItemMapper()
    private lateinit var bottomSheetView: BottomSheetDialogLayoutBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetView =
            BottomSheetDialogLayoutBinding.inflate(layoutInflater, binding.root, false)
        bottomSheetDialog.setContentView(bottomSheetView.root);
        //TODO: Refactor

    }

    private val partyListRecyclerViewAdapter by lazy {
        PartyListRecyclerViewAdapter({ party ->
            if (party?.appliedUserIdList?.containsKey("adnbal89") == false) {
                bottomSheetView.textViewApply.visibility = View.VISIBLE
            } else {
                bottomSheetView.textViewApply.visibility = View.GONE
            }
            bottomSheetDialog.show();

            bottomSheetView.textViewApply.setOnClickListener {
                viewModel.applyToParty(party?.id)
                party?.appliedUserIdList?.set("adnbal89", true)
            }
            bottomSheetView.textViewHide.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            bottomSheetView.textViewAddFavorite.setOnClickListener {
                bottomSheetDialog.dismiss()
                //TODO: //implement party application process.
            }
        }, { party ->
            val intent = Intent(requireActivity(), PartyDetailActivity::class.java)
            intent.putExtra("party", party)
            requireContext().startActivity(intent)
        })
    }


    override fun initViews() {

        with(binding.recyclerViewPartyList) {
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
            setDivider(drawableRes = R.drawable.bg_divider, showLastDivider = false)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = partyListRecyclerViewAdapter.also {
                it.stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }
        }
    }

    override fun observeEvents() {
        super.observeEvents()

        //Collect Party
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.partyList.collect { partyList ->
                    partyArrayList = ArrayList<PartyMapItem>()
                    with(binding) {
                        partyListRecyclerViewAdapter.setItems(partyList)
                    }
                    partyList.forEach {
                        //map [Party] to [PartyMapItem] while adding to arraylist
                        partyArrayList.add(partyMapper.map(it))
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect {
                    when (it) {
                        is HomeViewModel.Event.ErrorOccurred -> TODO()
                        is HomeViewModel.Event.PartyApplicationSuccessfullyCompleted -> {
                            showSnackbar("You have applied to Party")
                            bottomSheetView.textViewApply.visibility = View.GONE
                            bottomSheetDialog.dismiss()
                        }
                    }
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
            val intent = Intent(requireActivity(), PartyMapsActivity::class.java)
            intent.putExtra("partyList", partyArrayList)
            requireContext().startActivity(intent)
            true
        }
        super.onCreateOptionsMenu(menu, inflater)
    }
}

