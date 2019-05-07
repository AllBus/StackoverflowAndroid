package com.kos.exercise.utils

import android.annotation.SuppressLint

object Converter {

    @SuppressLint("SimpleDateFormat")
    val fullDateFormat = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    fun toDate(unixDate: Long): CharSequence {

        val date = java.util.Date(unixDate * 1000L)
        return fullDateFormat.format(date)
    }
}