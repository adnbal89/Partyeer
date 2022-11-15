package com.partyeer.presentation.ui.main.features.party.searchparty

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.partyeer.domain.repository.party.model.Party
import com.partyeer.presentation.databinding.ItemLayoutSearchPartyBinding
import com.partyeer.presentation.ui.main.extension.inflater
import com.partyeer.presentation.ui.main.view.recycler.BaseRecyclerViewAdapter
import com.partyeer.presentation.ui.main.view.recycler.BaseRecyclerViewHolder
import com.partyeer.presentation.ui.main.view.recycler.ViewBindingRecyclerViewHolder

class SearchPartyRecyclerViewAdapter(
    private val clickListener: (Party?) -> Unit
) : BaseRecyclerViewAdapter<Party>() {

    override fun createNewViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<Party> = SearchPartyViewHolder(parent) { position ->
        clickListener(getItem(position))
    }
}

private class SearchPartyViewHolder(
    parent: ViewGroup,
    clickAtPosition: (Int) -> Unit
) : ViewBindingRecyclerViewHolder<Party, ItemLayoutSearchPartyBinding>(
    ItemLayoutSearchPartyBinding.inflate(parent.inflater(), parent, false)
) {
    init {
        itemBinding.layoutHeadLines.setOnClickListener {
            clickAtPosition(bindingAdapterPosition)
        }
    }

    override fun bindItem(item: Party) {
        with(itemBinding) {
            Glide.with(context).load(item.pictures[0].preview)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageViewPartyLogo)
            textViewPartyTitle.text = item.title + ", " + item.address

        }
    }

}