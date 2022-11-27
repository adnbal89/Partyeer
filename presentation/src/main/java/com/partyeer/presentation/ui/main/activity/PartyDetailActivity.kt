package com.partyeer.presentation.ui.main.activity

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.ActivityPartyDetailBinding
import com.partyeer.presentation.ui.main.base.BaseActivity
import com.partyeer.presentation.ui.main.features.party.createparty.PictureRecyclerViewAdapter
import com.partyeer.presentation.ui.main.features.party.details.PartyDetailViewModel
import com.partyeer.presentation.ui.main.features.party.details.PartyTagRecyclerViewAdapter
import com.partyeer.presentation.ui.main.util.navigation.Navigator
import com.partyeer.presentation.ui.main.util.setDivider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PartyDetailActivity : BaseActivity() {
    private val viewModel: PartyDetailViewModel by viewModels()

    private lateinit var binding: ActivityPartyDetailBinding
    private lateinit var party: Party
    private val pictureRecyclerViewAdapter = PictureRecyclerViewAdapter()
    private val partyTagRecyclerViewAdapter by lazy {
        PartyTagRecyclerViewAdapter { tag ->
            navigator.toSameTaggedPartiesActivity(tag!!).navigate()
        }
    }

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        party = intent.getParcelableExtra<Party>("party").let { it!! }
        pictureRecyclerViewAdapter.setItems(party.pictures)
        partyTagRecyclerViewAdapter.setItems(party.tagList.keys)

        supportActionBar?.title = party.title

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
            textViewEntryFeeValue.text = party.entranceFee
            textViewDescriptionValue.text = party.description

            with(viewPagerPartyDetail) {
                adapter = pictureRecyclerViewAdapter
                setPictureIndicatorText(currentItem, party.pictures.size.coerceAtLeast(1))

                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        setPictureIndicatorText(position, party.pictures.size.coerceAtLeast(1))
                    }
                })
            }

            with(recyclerViewTags) {
                setDivider(drawableRes = R.drawable.bg_divider, showLastDivider = false)
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = partyTagRecyclerViewAdapter
            }

            imageViewApprovedUsers.setOnClickListener {
                val intent = Intent(this@PartyDetailActivity, InviteeListActivity::class.java)
                intent.putExtra("partyInviteeList", party.inviteeList)
                startActivity(intent)
            }
        }
    }

    fun setPictureIndicatorText(position: Int, pictureCount: Int) {
        binding.textViewPictureIndicator.text = this.getString(
            R.string.picture_indicator,
            position + 1,
            pictureCount
        )
    }

}