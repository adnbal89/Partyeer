package com.partyeer.presentation.ui.main.base.delegate

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.partyeer.presentation.ui.main.util.ViewBindingUtil
import com.partyeer.util.functional.getGenericType
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<VB : ViewBinding>(
    private val fragment: Fragment
) : ReadOnlyProperty<Fragment, VB>, DefaultLifecycleObserver {

    private var binding: VB? = null
        get() = field ?: createViewBinding().also { viewBinding ->
            field = viewBinding
        }

    init {
        fragment.lifecycle.addObserver(this)
    }

    override fun getValue(
        thisRef: Fragment,
        property: KProperty<*>
    ): VB = binding!!

    private fun createViewBinding(): VB {
        return ViewBindingUtil.getViewBinding<VB>(
            viewBindingClass = fragment.javaClass.getGenericType(ViewBinding::class.java),
            layoutInflater = fragment.layoutInflater,
            parent = null,
            attachToParent = false
        )
    }

    override fun onDestroy(owner: LifecycleOwner) {
        binding = null
        fragment.lifecycle.removeObserver(this)
    }
}