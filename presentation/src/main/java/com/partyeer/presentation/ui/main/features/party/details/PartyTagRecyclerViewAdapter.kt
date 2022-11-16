package com.partyeer.presentation.ui.main.features.party.details

import android.view.ViewGroup
import com.partyeer.presentation.databinding.ItemLayoutPartyTagsBinding
import com.partyeer.presentation.ui.main.extension.inflater
import com.partyeer.presentation.ui.main.view.recycler.BaseRecyclerViewAdapter
import com.partyeer.presentation.ui.main.view.recycler.BaseRecyclerViewHolder
import com.partyeer.presentation.ui.main.view.recycler.ViewBindingRecyclerViewHolder

class PartyTagRecyclerViewAdapter(
    private val clickListener: (String?) -> Unit
) : BaseRecyclerViewAdapter<String>() {
    override fun createNewViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<String> = PartyTagViewHolder(parent) { position ->
        clickListener(getItem(position))
    }
}

private class PartyTagViewHolder(
    parent: ViewGroup,
    clickAtPosition: (Int) -> Unit,
) : ViewBindingRecyclerViewHolder<String, ItemLayoutPartyTagsBinding>(
    ItemLayoutPartyTagsBinding.inflate(parent.inflater(), parent, false)
) {
    init {
        itemBinding.buttonTag.setOnClickListener {
            clickAtPosition(bindingAdapterPosition)
        }
    }

    override fun bindItem(item: String) {
        with(itemBinding) {
            buttonTag.text = item
        }
    }

}
