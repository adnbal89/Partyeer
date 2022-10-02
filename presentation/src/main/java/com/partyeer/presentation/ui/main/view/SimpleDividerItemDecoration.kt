package com.partyeer.presentation.ui.main.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class SimpleDividerItemDecoration(
    context: Context,
    @DrawableRes drawableRes: Int?,
    var showLastDivider: Boolean = false
) : RecyclerView.ItemDecoration() {

    private val divider: Drawable? = when {
        drawableRes != null -> {
            ContextCompat.getDrawable(context, drawableRes)
        }
        else -> null
    }

    private val bounds = Rect()

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (divider == null || parent.layoutManager == null) {
            return
        }

        drawVertical(canvas, parent, divider)
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView, divider: Drawable) {
        canvas.save()

        val left: Int
        val right: Int

        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(
                left,
                parent.paddingTop,
                right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            if (!showLastDivider && i == childCount - 1) {
                continue
            }

            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, bounds)
            val bottom = bounds.bottom + child.translationY.roundToInt()
            val top = bottom - divider.intrinsicHeight

            divider.setBounds(left, top, right, bottom)
            divider.draw(canvas)
        }

        canvas.restore()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when {
            divider == null -> outRect.set(0, 0, 0, 0)
            else -> outRect.set(0, 0, 0, divider.intrinsicHeight)
        }
    }

}