package com.partyeer.presentation.ui.main.base.delegate

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.partyeer.presentation.ui.main.base.BaseViewModel
import com.partyeer.util.functional.getGenericType
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewModelDelegate<VM : BaseViewModel>(
    private val fragment: Fragment
) : ReadOnlyProperty<Fragment, VM>, DefaultLifecycleObserver {

    private var viewModel: VM? = null
        get() = field ?: createViewModel().also {
            field = it
        }

    init {
        fragment.lifecycle.addObserver(this)
    }

    override fun onCreate(owner: LifecycleOwner) {
        viewModel?.onViewAttached()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        viewModel?.onViewDetached()
        viewModel = null
        fragment.lifecycle.removeObserver(this)
    }

    override fun getValue(
        thisRef: Fragment,
        property: KProperty<*>
    ): VM = viewModel!!


    private fun createViewModel(): VM {
        val viewModelStore = fragment.viewModelStore
        val viewModelProviderFactory = fragment.defaultViewModelProviderFactory

        return ViewModelProvider(
            viewModelStore,
            viewModelProviderFactory
        ).get(fragment.javaClass.getGenericType<VM>(BaseViewModel::class.java))
    }
}

fun <VM : BaseViewModel> Fragment.viewModel() =
    FragmentViewModelDelegate<VM>(this)