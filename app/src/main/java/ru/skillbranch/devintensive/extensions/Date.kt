package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.ceil

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern : String = "HH:mm:ss dd.MM.yy") : String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value : Int, units : TimeUnits) : Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        else -> throw IllegalStateException("invalid unit")
    }
    this.time = time
    return this
}

fun Date.humanizeDiff() : String {
    val delta = Date().time - this.time
    val deltaString = when (delta.absoluteValue) {
        in 0 .. 1 * SECOND -> "только что"
        in 1 * SECOND .. 45 * SECOND -> "несколько секунд"
        in 45 * SECOND .. 75 * SECOND -> "минуту"
        in 75 * SECOND .. 45 * MINUTE -> TimeUnits.MINUTE.plural(ceil(1.0 * delta.absoluteValue / MINUTE).toInt())
        in 45 * MINUTE .. 75 * MINUTE -> "час"
        in 75 * MINUTE .. 22 * HOUR -> TimeUnits.HOUR.plural(ceil(1.0 * delta.absoluteValue / HOUR).toInt())
        in 22 * HOUR .. 26 * HOUR -> "день"
        in 26 * HOUR .. 360 * DAY -> TimeUnits.DAY.plural(ceil(1.0 * delta.absoluteValue / DAY).toInt())
        else -> "что-то не так со временем"
    }
    if (delta < -360 * DAY) return "более чем через год"
    if (delta > 360 * DAY) return "более года назад"
    if (delta.absoluteValue in 0 .. 1 * SECOND) return deltaString
    return if (delta > 0) "$deltaString назад" else "через $deltaString"
}

enum class TimeUnits {
    SECOND {
        override fun plural(value : Int) : String {
            return pluralSelect(value, "секунду", "секунды", "секунд")
        }
    },
    MINUTE {
        override fun plural(value : Int) : String {
            return pluralSelect(value, "минуту", "минуты", "минут")
        }
    },
    HOUR {
        override fun plural(value : Int) : String {
            return pluralSelect(value, "час", "часа", "часов")
        }
    },
    DAY {
        override fun plural(value : Int) : String {
            return pluralSelect(value, "день", "дня", "дней")
        }
    };

    abstract fun plural(value : Int) : String

    fun pluralSelect(value : Int, one : String, few : String, many : String) : String {
        return when(value % 100) {
            in 11 .. 19 -> "$value $many"
            else -> when(value % 10) {
                1 -> "$value $one"
                in 2 .. 4 -> "$value $few"
                else -> "$value $many"
            }
        }
    }
}