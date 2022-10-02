package com.partyeer.presentation.ui.main.util

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.partyeer.presentation.ui.main.view.SimpleDividerItemDecoration

fun RecyclerView.setDivider(
    @DrawableRes drawableRes: Int? = null,
    showLastDivider: Boolean = true
) {
    this.addItemDecoration(
        SimpleDividerItemDecoration(
            context = context,
            drawableRes = drawableRes,
            showLastDivider = showLastDivider
        )
    )
}