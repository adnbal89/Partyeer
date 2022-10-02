package com.partyeer.util.formatter

import java.text.SimpleDateFormat
import java.util.*


object FileNameFormatter {

    private var formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS", Locale.getDefault())
    private val now = Date()


    fun getFormattedFileName(): String {
        val now = Date()
        val fileName = formatter.format(now)

        return fileName
    }
}