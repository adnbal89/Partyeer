package com.partyeer.presentation.ui.main.view.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Base RecyclerViewAdapter that uses BaseRecyclerViewHolder for click and long click support.
 */
abstract class BaseRecyclerViewAdapter<T>(
    items: List<T>? = null,
    private val diffCallback: DiffCallback<T> = DiffCallback(),
    var onItemClick: ((T) -> Unit)? = null,
    var onItemLongClick: ((T) -> Unit)? = null,
) : RecyclerView.Adapter<BaseRecyclerViewHolder<T>>() {

    private val items = ArrayList<T>()

    init {
        items?.let {
            this.items.addAll(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<T> {
        val viewHolder = createNewViewHolder(parent, viewType)

        onItemClick?.let { listener ->
            viewHolder.setItemClickListener { position ->
                getItem(position)?.let { item ->
                    listener(item)
                }
            }
        }

        onItemLongClick?.let { listener ->
            viewHolder.setItemClickListener { position ->
                getItem(position)?.let { item ->
                    listener(item)
                }
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: BaseRecyclerViewHolder<T>, position: Int) {
        getItem(position)?.let { item ->
            viewHolder.bindItem(item)
        }
    }

    override fun getItemCount(): Int = items.size

    /**
     * Returns the items in the data set held by this adapter.
     *
     * @return The items in this adapter.
     */
    fun getItems(): ArrayList<T> {
        return ArrayList(items)
    }

    /**
     * Returns the items that are filtered by class in the data set held by this adapter.
     *
     * @return The items by type.
     */
    fun getItems(predicate: (T) -> Boolean): List<T> {
        return getItems().filter(predicate)
    }

    /**
     * Returns the item at the specified position in this adapter.
     *
     * @param position position of the item to return
     * @return the item at the specified position in this adapter
     */
    open fun getItem(position: Int): T? {
        return items.getOrNull(position)
    }

    /**
     * Sets new items to display in this adapter.
     *
     * @param items The items to display in this adapter.
     */
    open fun setItems(items: Collection<T>?) {
        val diffResult = DiffUtil.calculateDiff(diffCallback.update(items?.toList()))
        this.items.clear()
        this.items.addAll(items ?: arrayListOf())
        diffResult.dispatchUpdatesTo(this)
    }

    /**
     * Add new items to display in this adapter.
     *
     * @param items The items to display in this adapter.
     */
    fun addItems(items: Collection<T>) {
        val previousSize = itemCount
        this.items.addAll(items)
        notifyItemRangeInserted(previousSize, items.size)
    }

    /**
     * Add a new item to display in this adapter.
     *
     * @param item The item to add.
     */
    fun addItem(item: T, position: Int) {
        this.items.add(position, item)
        notifyItemInserted(position)
    }

    /**
     * Update an item to display in this adapter.
     *
     * @param item The item to be updated.
     */
    fun updateItem(item: T) {
        indexOf(item)?.let { index ->
            updateItem(item, index)
        }
    }

    /**
     * Update the item at position in this adapter.
     *
     * @param item The item to be updated.
     * @param position Position of the item to be updated
     */
    fun updateItem(item: T, position: Int) {
        this.items[position] = item
        notifyItemChanged(position)
    }

    /**
     * Remove the item at position from this adapter.
     *
     * @param position The position at which the item to remove.
     */
    fun removeItem(position: Int) {
        this.items.removeAt(position)
        notifyItemRemoved(position)
    }

    /**
     * Remove an item displaying in this adapter.
     *
     * @param item The item to remove.
     */
    fun removeItem(item: T) {
        indexOf(item)?.let { position ->
            removeItem(position)
        }
    }

    fun indexOf(item: T): Int? {
        val index = items.indexOf(item)
        return when {
            index < 0 -> null
            else -> index
        }
    }

    /**
     * Clear all items in this adapter.
     */
    fun clearItems() {
        setItems(emptyList())
    }

    /**
     * Returns true if the items in this adapter is empty.
     *
     * @return true if the items in this adapter is empty.
     */
    fun isEmpty(): Boolean {
        return items.isNullOrEmpty()
    }

    /**
     * Register a callback to be invoked when an item is clicked.
     *
     * @param onItemClick callback
     */
    fun setItemClickListener(onItemClick: ((T) -> Unit)) {
        this.onItemClick = onItemClick
    }

    /**
     * Register a callback to be invoked when an item is clicked and held.
     *
     * @param onItemLongClick callback
     */
    fun setItemLongClickListener(onItemLongClick: ((T) -> Unit)) {
        this.onItemLongClick = onItemLongClick
    }

    /**
     * Called when a new ViewHolder of the given type is needed to represent the RecyclerView item.
     *
     * @param parent   parent layout
     * @param viewType the integer value identifying the type of the view
     * @return a new viewHolder instance
     */
    protected abstract fun createNewViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<T>
}

open class DiffCallback<T> : DiffUtil.Callback() {
    private var oldList: List<T> = emptyList()
    private var newList: List<T> = emptyList()

    fun update(newList: List<T>?): DiffCallback<T> {
        this.oldList = ArrayList(this.newList)
        this.newList = ArrayList(newList ?: emptyList())
        return this
    }

    protected fun oldItem(itemPosition: Int): T? =
        oldList.getOrNull(itemPosition)

    protected fun newItem(itemPosition: Int): T? =
        newList.getOrNull(itemPosition)

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem(oldItemPosition) === newItem(newItemPosition)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem(oldItemPosition) == newItem(newItemPosition)
    }
}
