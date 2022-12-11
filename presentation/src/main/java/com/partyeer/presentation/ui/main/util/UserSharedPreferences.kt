package com.partyeer.presentation.ui.main.util

import android.content.Context
import android.content.SharedPreferences

class UserSharedPreferences(
    val context: Context
) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("partyeerUserPreferences", Context.MODE_PRIVATE)

    var userName: String?
        get() = sharedPreferences.getString("username", null)
        set(value) = sharedPreferences.edit().putString("username", value).apply()

}