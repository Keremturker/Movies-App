package in_.turker.moviesapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Kerem TÜRKER on 12.03.2022.
 */

fun String.convertToDateFormat(fromFormat: String, toFormat: String): String {
    val newDate = toDate(fromFormat)
    return newDate?.toFormattedString(toFormat) ?: ""
}

fun String.toDate(format: String, defaultDate: Date? = null): Date? {
    val locale = Locale("tr")
    return try {
        SimpleDateFormat(format, locale).parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
        defaultDate ?: Date()
    }
}

@SuppressLint("SimpleDateFormat")
fun Date.toFormattedString(format: String): String {
    return try {
        SimpleDateFormat(format).format(this)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}
