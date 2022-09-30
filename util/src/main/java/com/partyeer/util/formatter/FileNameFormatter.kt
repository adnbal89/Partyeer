package com.partyeer.util.formatter

import java.text.SimpleDateFormat
import java.util.*


object FileNameFormatter{

    private var formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
    private val now = Date()
    private val fileName = formatter.format(now)


    fun getFormattedFileName(): String{
        return fileName
    }
}