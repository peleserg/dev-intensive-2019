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

// Реализуй extension Date.humanizeDiff(date) (значение по умолчанию текущий момент времени) для форматирования
// вывода разницы между датами в человекообразном формате, с учетом склонения числительных. Временные интервалы
// преобразований к человекообразному формату доступны в ресурсах к заданию
// Пример:
// Date().add(-2, TimeUnits.HOUR).humanizeDiff() //2 часа назад
// Date().add(-5, TimeUnits.DAY).humanizeDiff() //5 дней назад
// Date().add(2, TimeUnits.MINUTE).humanizeDiff() //через 2 минуты
// Date().add(7, TimeUnits.DAY).humanizeDiff() //через 7 дней
// Date().add(-400, TimeUnits.DAY).humanizeDiff() //более года назад
// Date().add(400, TimeUnits.DAY).humanizeDiff() //более чем через год
// 0с - 1с "только что" +
// 1с - 45с "несколько секунд назад" +
// 45с - 75с "минуту назад" +
// 75с - 45мин "N минут назад" +
// 45мин - 75мин "час назад" +
// 75мин 22ч "N часов назад"
// 22ч - 26ч "день назад"
// 26ч - 360д "N дней назад"
// >360д "более года назад" +

fun Date.humanizeDiff() : String {
    val delta = Date().time - this.time
    val minutes = ceil(1.0 * delta.absoluteValue / MINUTE).toInt()
    val hours = ceil(1.0 * delta.absoluteValue / HOUR).toInt()
    val days = ceil(1.0 * delta.absoluteValue / DAY).toInt()
    val deltaString = when (delta.absoluteValue) {
        in 0 .. 1 * SECOND -> "только что"
        in 1 * SECOND .. 45 * SECOND -> "несколько секунд"
        in 45 * SECOND .. 75 * SECOND -> "минуту"
        in 75 * SECOND .. 45 * MINUTE -> when(minutes % 100) {
            in 11 .. 19 -> "$minutes минут"
            else -> when(minutes % 10) {
                1 -> "$minutes минуту"
                in 2 .. 4 -> "$minutes минуты"
                else -> "$minutes минут"
            }
        }
        in 45 * MINUTE .. 75 * MINUTE -> "час"
        in 75 * MINUTE .. 22 * HOUR -> when(hours % 100) {
            in 11 .. 19 -> "$hours часов"
            else -> when(hours % 10) {
                1 -> "$hours час"
                in 2 .. 4 -> "$hours часа"
                else -> "$hours часов"
            }
        }
        in 22 * HOUR .. 26 * HOUR -> "день"
        in 26 * HOUR .. 360 * DAY -> when(days % 100) {
            in 11 .. 19 -> "$days дней"
            else -> when(days % 10) {
                1 -> "$days день"
                in 2 .. 4 -> "$days дня"
                else -> "$days дней"
            }
        }
        else -> "что-то не так со временем"
    }
    if (delta < -360 * DAY) return "более чем через год"
    if (delta > 360 * DAY) return "более года назад"
    if (delta.absoluteValue in 0 .. 1 * SECOND) return deltaString
    return if (delta > 0) "$deltaString назад" else "через $deltaString"
}