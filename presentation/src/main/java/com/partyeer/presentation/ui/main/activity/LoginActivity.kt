package com.partyeer.presentation.ui.main.activity

import android.content.Context
import android.content.Intent
import com.partyeer.presentation.ui.main.base.BaseActivity
import com.partyeer.presentation.ui.main.util.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    @Inject
    lateinit var navigator: Navigator

    companion object {
        fun callingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

}