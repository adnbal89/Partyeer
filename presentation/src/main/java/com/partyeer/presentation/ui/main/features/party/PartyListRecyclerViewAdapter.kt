package com.partyeer.presentation.ui.main.features.party

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

class PartyListRecyclerViewAdapter : BaseRecyclerViewAdapter<Party>() {


    override fun createNewViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<Party> = PartyViewHolder(parent)

}

private class PartyViewHolder(
    parent: ViewGroup,
) : ViewBindingRecyclerViewHolder<Party, ItemLayoutPartyBinding>(
    ItemLayoutPartyBinding.inflate(parent.inflater(), parent, false)
) {

    override fun bindItem(item: Party) {

        val pictureRecyclerViewAdapter = PictureRecyclerViewAdapter()
        pictureRecyclerViewAdapter.setItems(item.pictures)

        with(itemBinding.viewPagerPictures) {
            adapter = pictureRecyclerViewAdapter
            setPictureIndicatorText(position, item.pictures.size.coerceAtLeast(1))

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    setPictureIndicatorText(position, item.pictures.size.coerceAtLeast(1))
                }
            })
        }

        itemBinding.textViewPartyTitle.text = item.title
        itemBinding.textViewPartyLocation.text = "Berlin, Germany"
        itemBinding.textViewPartyConcept.text = item.concept.description
        itemBinding.textViewPartyTime.text = item.timeStart.toString()

    }

    fun setPictureIndicatorText(position: Int, pictureCount: Int) {
        itemBinding.textViewPictureIndicator.text = context.getString(
            R.string.picture_indicator,
            position + 1,
            pictureCount
        )
    }
}