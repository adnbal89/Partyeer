package com.partyeer.presentation.ui.main.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import com.partyeer.presentation.ui.main.base.delegate.FragmentViewBindingDelegate

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

fun <VB : ViewBinding> Fragment.viewBinding() =
    FragmentViewBindingDelegate<VB>(this)


