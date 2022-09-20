package com.partyeer.presentation.ui.main.view.recycler

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.partyeer.presentation.ui.main.extension.inflater
import com.partyeer.presentation.ui.main.util.ViewBindingUtil
import com.partyeer.util.functional.getGenericType

abstract class ViewBindingRecyclerViewHolder<T, VB : ViewBinding>(
    protected val itemBinding: VB
) : BaseRecyclerViewHolder<T>(itemBinding.root) {

    constructor(parent: ViewGroup) : this(
        ViewBindingUtil.getViewBinding<VB>(
            viewBindingClass = ViewBindingRecyclerViewHolder::class.java.getGenericType(ViewBinding::class.java),
            layoutInflater = parent.inflater(),
            parent = parent,
            attachToParent = false
        )
    )
}