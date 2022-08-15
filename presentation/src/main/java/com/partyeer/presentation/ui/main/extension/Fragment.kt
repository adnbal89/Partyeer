package com.partyeer.presentation.ui.main.extension

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.partyeer.presentation.ui.main.base.delegate.FragmentViewBindingDelegate

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

fun <VB : ViewBinding> Fragment.viewBinding() =
    FragmentViewBindingDelegate<VB>(this)

fun Fragment.showSnackbar(
    message: String,
    duration: Int = Snackbar.LENGTH_LONG,
    view: View = requireView()
) {
    Snackbar.make(view, message, duration).show()
}

