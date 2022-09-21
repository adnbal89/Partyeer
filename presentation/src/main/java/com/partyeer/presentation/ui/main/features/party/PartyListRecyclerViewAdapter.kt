package com.partyeer.presentation.ui.main.features.party

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.presentation.databinding.ItemLayoutPartyBinding
import com.partyeer.presentation.ui.main.extension.inflater
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
        //bind banner logo
        Glide.with(context).load(item.logoUrl).into(itemBinding.imageViewPartyBanner)
        itemBinding.textViewPartyTitle.text = item.title
        itemBinding.textViewPartyLocation.text = "Berlin, Germany"
        itemBinding.textViewPartyConcept.text = item.concept
        itemBinding.textViewPartyTime.text = item.timeStart.toString()

    }

}