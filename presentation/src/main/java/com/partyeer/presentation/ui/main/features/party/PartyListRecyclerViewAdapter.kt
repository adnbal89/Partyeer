package com.partyeer.presentation.ui.main.features.party

import android.text.format.DateFormat
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.presentation.R
import com.partyeer.presentation.databinding.ItemLayoutPartyBinding
import com.partyeer.presentation.ui.main.extension.inflater
import com.partyeer.presentation.ui.main.features.party.createparty.PictureRecyclerViewAdapter
import com.partyeer.presentation.ui.main.view.recycler.BaseRecyclerViewAdapter
import com.partyeer.presentation.ui.main.view.recycler.BaseRecyclerViewHolder
import com.partyeer.presentation.ui.main.view.recycler.ViewBindingRecyclerViewHolder

class PartyListRecyclerViewAdapter(
    private val clickListener: (Party?) -> Unit
) : BaseRecyclerViewAdapter<Party>() {


    override fun createNewViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<Party> = PartyViewHolder(parent) { position ->
        clickListener(getItem(position))
    }
}


private class PartyViewHolder(
    parent: ViewGroup,
    clickAtPosition: (Int) -> Unit
) : ViewBindingRecyclerViewHolder<Party, ItemLayoutPartyBinding>(
    ItemLayoutPartyBinding.inflate(parent.inflater(), parent, false)
) {
    init {
        itemBinding.imageViewOptionsMenu.setOnClickListener {
            clickAtPosition(bindingAdapterPosition)
        }
    }

    override fun bindItem(item: Party) {

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
            textViewPartyLocation.text = "Berlin, Germany"
            textViewPartyTime.text = DateFormat.format(" dd/MM HH:mm", item.timeStart)
            textViewPartyLikeCounter.text = item.likeCount.toString()

            imageViewShare.setOnClickListener {

            }

            constraintLayoutPartyLocation.setOnClickListener {

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