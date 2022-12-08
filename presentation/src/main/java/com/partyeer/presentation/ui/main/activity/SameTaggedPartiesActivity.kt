package com.partyeer.presentation.ui.main.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.partyeer.presentation.databinding.ActivitySameTaggedPartiesBinding
import com.partyeer.presentation.ui.main.base.BaseActivity
import com.partyeer.presentation.ui.main.features.party.searchparty.BasicPartyRecyclerViewAdapter
import com.partyeer.presentation.ui.main.features.party.tag.SameTaggedPartiesViewModel
import com.partyeer.presentation.ui.main.util.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SameTaggedPartiesActivity : BaseActivity() {
    private val viewModel: SameTaggedPartiesViewModel by viewModels()
    private lateinit var binding: ActivitySameTaggedPartiesBinding
    private val partyListRecyclerViewAdapter by lazy {
        BasicPartyRecyclerViewAdapter{

        }
    }

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySameTaggedPartiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        with(binding.recyclerViewTaggedPartyList) {
            adapter = partyListRecyclerViewAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        val tag = intent.getStringExtra("tag")
        title = tag

        viewModel.getPartiesTaggedBy(tag!!)

        lifecycleScope.launch {
            viewModel.partyList.collect { partyList ->
                partyListRecyclerViewAdapter.setItems(partyList)
            }
        }

    }


}