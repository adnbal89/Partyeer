package com.partyeer.presentation.ui.main.util

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import com.partyeer.presentation.R
import com.partyeer.presentation.ui.main.view.ErrorDialog
import com.partyeer.presentation.ui.main.view.ProgressDialog
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ActivityScoped
class DialogManager @Inject constructor(
    @ActivityContext private val context: Context
) {
    private val showProgress = MutableStateFlow(false)
    private val error = MutableStateFlow<String>(String())

    fun init(composeView: ComposeView) {
        composeView.setContent {
            ProgressDialog(showProgress.collectAsState())
            ErrorDialog(error.collectAsState())
        }
    }

    fun showLoading() {
        showProgress.value = true
    }

    fun dismissLoading() {
        showProgress.value = false
    }

    fun showErrorDialog(error: Throwable) {
        showErrorDialog(error.message)
    }

    fun showErrorDialog(error: String?) {
        this.error.value = error ?: context.getString(R.string.error_unknown)
    }
}