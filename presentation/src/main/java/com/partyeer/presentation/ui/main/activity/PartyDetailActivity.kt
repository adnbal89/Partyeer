package com.partyeer.presentation.ui.main.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.presentation.databinding.ActivityPartyDetailBinding
import com.partyeer.presentation.ui.main.activity.viewmodel.PartyDetailViewModel
import com.partyeer.presentation.ui.main.base.BaseActivity
import com.partyeer.presentation.ui.main.features.party.createparty.PictureRecyclerViewAdapter

class PartyDetailActivity : BaseActivity() {
    private val viewModel: PartyDetailViewModel by viewModels()

    private lateinit var binding: ActivityPartyDetailBinding
    private lateinit var party: Party
    private val pictureRecyclerViewAdapter = PictureRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        party = intent.getParcelableExtra<Party>("party").let { it!! }

        pictureRecyclerViewAdapter.setItems(party.pictures)

        binding.textViewStartTimeValue.text = party.timeStart.toString()
        binding.textViewLocationValue.text = party.address
        binding.textViewEndTimeValue.text = party.timeEnd.toString()
        binding.textViewDescriptionValue.text = party.description

        with(binding) {
            with(viewPagerPartyDetail) {
                adapter = pictureRecyclerViewAdapter
            }
        }
    }

}