package cl.cdum.therickandmorty.utils.functions

import android.annotation.SuppressLint
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
fun dateTimeFormat(dateTime: String): String {
    val zonedTime = ZonedDateTime.parse(dateTime)
    return zonedTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
}