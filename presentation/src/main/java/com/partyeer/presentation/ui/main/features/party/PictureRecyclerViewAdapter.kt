package com.partyeer.presentation.ui.main.features.party.createparty

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.partyeer.domain.repository.party.model.Picture
import com.partyeer.presentation.databinding.ItemLayoutPictureBinding
import com.partyeer.presentation.ui.main.extension.inflater
import com.partyeer.presentation.ui.main.view.recycler.BaseRecyclerViewAdapter
import com.partyeer.presentation.ui.main.view.recycler.BaseRecyclerViewHolder
import com.partyeer.presentation.ui.main.view.recycler.ViewBindingRecyclerViewHolder

class PictureRecyclerViewAdapter : BaseRecyclerViewAdapter<Picture>() {
    override fun createNewViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<Picture> = PictureViewHolder(parent = parent)
}

private class PictureViewHolder(
    parent: ViewGroup
) : ViewBindingRecyclerViewHolder<Picture, ItemLayoutPictureBinding>(
    ItemLayoutPictureBinding.inflate(parent.inflater(), parent, false)
) {
    override fun bindItem(item: Picture) {
        Glide.with(context).load(item.preview).diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(itemBinding.imageViewPreview)
    }

}