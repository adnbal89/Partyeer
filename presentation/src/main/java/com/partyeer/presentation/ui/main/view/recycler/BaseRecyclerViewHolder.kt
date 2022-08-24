package com.partyeer.presentation.ui.main.view.recycler

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.partyeer.presentation.ui.main.extension.inflate


/**
 * A base ViewHolder that handles click and long click events.
 */
abstract class BaseRecyclerViewHolder<T> : RecyclerView.ViewHolder,
    View.OnClickListener, View.OnLongClickListener {

    val context: Context
        get() = itemView.context

    private var onItemClick: ((Int) -> Unit)? = null
    private var onItemLongClick: ((Int) -> Unit)? = null

    /**
     * Constructor.
     *
     * @param parentView parent
     * @param layoutId   layout resource to inflate
     */
    constructor(parentView: ViewGroup, @LayoutRes layoutId: Int)
            : this(parentView.inflate(layoutId))

    /**
     * Constructor.
     *
     * @param itemView item view
     */
    constructor(itemView: View) : super(itemView)
    /**
     * Calls to update the contents with the item.
     *
     * Override to set up some private fields to be used by RecyclerView.
     */
    abstract fun bindItem(item: T)
    /**
     * Register a callback to be invoked when this item is clicked.
     *
     * @param onItemClick callback
     */
    fun setItemClickListener(onItemClick: (Int) -> Unit) {
        this.onItemClick = onItemClick
        this.itemView.setOnClickListener(this)
    }

    /**
     * Register a callback to be invoked when this item is clicked and held.
     *
     * @param onItemLongClick callback
     */
    fun setItemLongClickListener(onItemLongClick: (Int) -> Unit) {
        this.onItemLongClick = onItemLongClick
        this.itemView.setOnLongClickListener(this)
    }

    override fun onClick(p0: View?) {
        onItemClick?.invoke(bindingAdapterPosition)
    }

    override fun onLongClick(p0: View?): Boolean {
        onItemLongClick?.invoke(bindingAdapterPosition)
        return true
    }
}

@Suppress("UNCHECKED_CAST")
fun BaseRecyclerViewHolder<*>.any(): BaseRecyclerViewHolder<Any> =
    this as BaseRecyclerViewHolder<Any>