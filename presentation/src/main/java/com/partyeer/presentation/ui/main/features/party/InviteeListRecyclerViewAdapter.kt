package com.partyeer.presentation.ui.main.features.party

import android.view.ViewGroup
import com.partyeer.presentation.databinding.ItemLayoutInviteeListBinding
import com.partyeer.presentation.ui.main.extension.inflater
import com.partyeer.presentation.ui.main.view.recycler.BaseRecyclerViewAdapter
import com.partyeer.presentation.ui.main.view.recycler.BaseRecyclerViewHolder
import com.partyeer.presentation.ui.main.view.recycler.ViewBindingRecyclerViewHolder

class InviteeListRecyclerViewAdapter(
    private val clickListener: (String?) -> Unit
) : BaseRecyclerViewAdapter<String>() {

    override fun createNewViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<String> = InviteeListViewHolder(parent) { position ->
        clickListener(getItem(position))
    }
}

private class InviteeListViewHolder(
    parent: ViewGroup,
    clickAtPosition: (Int) -> Unit,
) : ViewBindingRecyclerViewHolder<String, ItemLayoutInviteeListBinding>(
    ItemLayoutInviteeListBinding.inflate(parent.inflater(), parent, false)
) {
    init {
        itemBinding.buttonFollow.setOnClickListener {
            clickAtPosition(bindingAdapterPosition)
        }
    }

    override fun bindItem(item: String) {
        with(itemBinding) {
            textViewInviteeName.text = item
        }
    }

}