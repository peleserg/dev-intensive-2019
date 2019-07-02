package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern : String = "HH:mm:ss dd.MM.yy") : String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

//Реализуй extension Date.add(value, TimeUnits) добавляющий или вычитающий значение переданное первым аргументом
//в единицах измерения второго аргумента (enum TimeUnits [SECOND, MINUTE, HOUR, DAY]) и возвращающий
//модифицированный экземпляр Date
//Пример:
//Date().add(2, TimeUnits.SECOND) //Thu Jun 27 14:00:02 GST 2019
//Date().add(-4, TimeUnits.DAY) //Thu Jun 23 14:00:00 GST 2019

fun Date.add(value : Int, units : String) : Date {
    var time = this.time
    time += when (units) {
        "second", "seconds" -> value * SECOND
        "minute", "minutes" -> value * MINUTE
        "hour", "hours" -> value * HOUR
        "day", "days" -> value * DAY
        else -> throw IllegalStateException("invalid unit")
    }
    this.time = time
    return this
}

fun Date.humanizeDiff() : String {
    return ""
}