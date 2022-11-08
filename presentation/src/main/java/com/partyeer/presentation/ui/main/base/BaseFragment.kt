package com.partyeer.presentation.ui.main.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.partyeer.presentation.R
import com.partyeer.presentation.ui.main.extension.viewBinding
import com.partyeer.presentation.ui.main.features.party.home.HomeFragment
import com.partyeer.presentation.ui.main.util.DialogManager
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    protected val binding: VB by viewBinding()

    @Inject
    lateinit var dialogManager: DialogManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    /**
     * Initialize UI content elements.
     */
    protected abstract fun initViews()

    open fun showLoading() {
        dialogManager.showLoading()
    }

    open fun hideLoading() {
        dialogManager.dismissLoading()
    }

    open fun showError(error: Throwable) {
        dialogManager.dismissLoading()
        dialogManager.showErrorDialog(error)
    }

    open fun onBackPressed() {}

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

}

