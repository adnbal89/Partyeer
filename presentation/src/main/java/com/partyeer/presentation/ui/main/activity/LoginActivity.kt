package com.partyeer.presentation.ui.main.activity

import android.content.Context
import android.content.Intent
import com.partyeer.presentation.ui.main.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

}