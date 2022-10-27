package com.partyeer.presentation.ui.main.activity

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
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

        with(binding) {
            textViewStartTimeValue.text = party.timeStart.toString()
            textViewLocationValue.text = party.address
            textViewApprovedCount.text = party.inviteeList.count().toString()
            textViewLikeCount.text = party.likeCount.toString()
            textViewStartTimeValue.text =
                DateFormat.format("E, dd/MMM/yyyy HH:mm", party.timeStart)
            textViewEndTimeValue.text =
                DateFormat.format("E, dd/MMM/yyyy HH:mm", party.timeStart)
            textViewCreatorName.text = party.creatorUserId
            textViewEndTimeValue.text = party.timeEnd.toString()
            textViewDescriptionValue.text = party.description

            with(viewPagerPartyDetail) {
                adapter = pictureRecyclerViewAdapter
            }
            imageViewApprovedUsers.setOnClickListener {
                val intent = Intent(this@PartyDetailActivity, InviteeListActivity::class.java)
                intent.putExtra("partyInviteeList", party.inviteeList)
                startActivity(intent)
            }
        }
    }

}