package com.partyeer.presentation.ui.main.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.partyeer.presentation.ui.main.base.BaseActivity

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun callingIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

}