package com.partyeer.presentation.ui.main.features.party

import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.ItemLayoutPartyDetailedBinding
import com.partyeer.presentation.ui.main.extension.inflater
import com.partyeer.presentation.ui.main.features.party.createparty.PictureRecyclerViewAdapter
import com.partyeer.presentation.ui.main.view.recycler.BaseRecyclerViewAdapter
import com.partyeer.presentation.ui.main.view.recycler.BaseRecyclerViewHolder
import com.partyeer.presentation.ui.main.view.recycler.ViewBindingRecyclerViewHolder

class PartyListRecyclerViewAdapter(
    private val clickListener: (Party?) -> Unit,
    private val clickPartyTitleListener: (Party?) -> Unit,
    private val clickPartyLocationListener: (Party?) -> Unit,
) : BaseRecyclerViewAdapter<Party>() {

    override fun createNewViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<Party> = PartyViewHolder(parent,
        { position -> clickListener(getItem(position)) },
        { position -> clickPartyTitleListener(getItem(position)) },
        { position -> clickPartyLocationListener(getItem(position)) })
}


private class PartyViewHolder(
    parent: ViewGroup,
    clickAtPosition: (Int) -> Unit,
    clickPartyTitleAtPosition: (Int) -> Unit,
    clickPartyLocationAtPosition: (Int) -> Unit
) : ViewBindingRecyclerViewHolder<Party, ItemLayoutPartyDetailedBinding>(
    ItemLayoutPartyDetailedBinding.inflate(parent.inflater(), parent, false)
) {
    init {
        itemBinding.imageViewOptionsMenu.setOnClickListener {
            clickAtPosition(bindingAdapterPosition)
        }

        itemBinding.textViewPartyTitle.setOnClickListener {
            clickPartyTitleAtPosition(bindingAdapterPosition)
        }

        itemBinding.constraintLayoutPartyLocation.setOnClickListener {
            clickPartyLocationAtPosition(bindingAdapterPosition)
        }
    }

    override fun bindItem(item: Party) {
        //TODO: refactor
        val pictureRecyclerViewAdapter = PictureRecyclerViewAdapter()
        pictureRecyclerViewAdapter.setItems(item.pictures)

        with(itemBinding) {

            with(viewPagerPictures) {
                adapter = pictureRecyclerViewAdapter
                setPictureIndicatorText(position, item.pictures.size.coerceAtLeast(1))

                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        setPictureIndicatorText(position, item.pictures.size.coerceAtLeast(1))
                    }
                })
            }
            textViewPartyTitle.text = item.title
            textViewPartyConcept.text = item.concept.description
            textViewPartyLocation.text = item.address.adminArea
            textViewPartyTime.text = item.formattedDate.toString()
            textViewPartyLikeCounter.text = item.likeCount.toString()

            imageViewShare.setOnClickListener {

            }

            imageViewPartyCreatorLogo.setOnClickListener {

            }
        }
    }

    fun setPictureIndicatorText(position: Int, pictureCount: Int) {
        itemBinding.textViewPictureIndicator.text = context.getString(
            R.string.picture_indicator,
            position + 1,
            pictureCount
        )
    }
}