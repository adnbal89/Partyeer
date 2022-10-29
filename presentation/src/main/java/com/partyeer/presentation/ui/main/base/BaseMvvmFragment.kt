package com.partyeer.presentation.ui.main.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.partyeer.presentation.ui.main.base.delegate.viewModel
import com.partyeer.util.exception.Failure

abstract class BaseMvvmFragment<VB : ViewBinding, VM : BaseViewModel> : BaseFragment<VB>() {

    protected val viewModel: VM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewModelAttached()
        observeEvents()
    }

    @CallSuper
    protected open fun observeEvents() {
        //--------------stateflow-------
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loading.collect { loading ->
                when (loading) {
                    true -> showLoading()
                    else -> hideLoading()
                }
            }
        }
        //--------------stateflow-------


        //------------------ livedata-------
        /*viewModel.loading.observe(viewLifecycleOwner) { loading ->
            when (loading) {
                true -> showLoading()
                else -> hideLoading()
            }
        }*/
        //------------------ livedata-------


        //--------------stateflow-------
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.error.collect {
                if (it != Failure.DefaultError)
                    showError(it)
            }
        }
        //--------------stateflow-------


        //------------------ livedata-------
        /*viewModel.error.observe(viewLifecycleOwner){
            showError(it)
        }*/
        //------------------ livedata-------
    }

    //TODO: party application function to be implemented
    fun applyToParty(partyId: String) {
    }

    open fun onViewModelAttached() {
        // Override if needed
    }

    open fun onViewModelDetached() {
        // Override if needed
    }
}