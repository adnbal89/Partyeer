package com.partyeer.presentation.ui.main.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

internal object ViewBindingUtil {

    @Suppress("UNCHECKED_CAST")
    fun <VB : ViewBinding> getViewBinding(
        viewBindingClass: Class<VB>,
        layoutInflater: LayoutInflater,
        parent: ViewGroup?,
        attachToParent: Boolean
    ): VB {
        val method = viewBindingClass.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return method(null, layoutInflater, parent, attachToParent) as VB
    }
}