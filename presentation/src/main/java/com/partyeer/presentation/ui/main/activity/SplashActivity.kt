package com.partyeer.presentation.ui.main.activity

import com.partyeer.presentation.ui.main.base.BaseActivity
import com.partyeer.presentation.ui.main.util.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    @Inject
    lateinit var navigator: Navigator

}