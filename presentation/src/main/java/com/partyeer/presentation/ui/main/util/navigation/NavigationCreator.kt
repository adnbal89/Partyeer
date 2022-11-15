package com.partyeer.presentation.ui.main.util.navigation

import android.app.Activity
import android.content.Intent

class NavigationCreator constructor(private val activity: Activity) {

    private var intent: Intent? = null
    private var animationPair: Pair<Int, Int>? = null
    private var requestCode: Int = 0
    private var forResult = false
    private var finishThis = false

    fun navigate() {

        intent?.let { intent ->
            if (forResult) {
                activity.startActivityForResult(intent, requestCode)
            } else {
                activity.startActivity(intent)
            }
        }

        animationPair?.let { pair ->
            activity.overridePendingTransition(pair.first, pair.second)
        }

        if (finishThis) {
            activity.finish()
        }
    }

    fun intent(intent: Intent): NavigationCreator {
        this.intent = intent
        return this
    }

    fun clearBackStack(): NavigationCreator {
        intent!!.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        return this
    }
}
