package ru.skillbranch.devintensive.utils

import android.content.res.Resources
import android.util.TypedValue

object Utils {
    fun parseFullName(fullName : String?) : Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(" ")
        val firstName = if (parts?.getOrNull(0) == "") null else parts?.getOrNull(0)
        val lastName = if (parts?.getOrNull(1) == "") null else parts?.getOrNull(1)
        return firstName to lastName
    }

    fun toInitials(firstName : String?, lastName : String?) : String? {
        val firstInitial = if (firstName?.trim()?.length != 0) firstName?.trim()?.substring(0, 1) else null
        val secondInitial = if (lastName?.trim()?.length != 0) lastName?.trim()?.substring(0, 1) else null
        return if (firstInitial == null && secondInitial == null) null else (firstInitial?.toUpperCase() ?: "") + (secondInitial?.toUpperCase() ?: "")
    }

    fun transliteration(payload : String?, divider : String = " ") : String? {
        if (payload == null) return null
        var result = ""
        payload.forEach { char ->
            result += when (char) {
                ' ' -> divider
                'а' -> "a"
                'б' -> "b"
                'в' -> "v"
                'г' -> "g"
                'д' -> "d"
                'е' -> "e"
                'ё' -> "e"
                'ж' -> "zh"
                'з' -> "z"
                'и' -> "i"
                'й' -> "i"
                'к' -> "k"
                'л' -> "l"
                'м' -> "m"
                'н' -> "n"
                'о' -> "o"
                'п' -> "p"
                'р' -> "r"
                'с' -> "s"
                'т' -> "t"
                'у' -> "u"
                'ф' -> "f"
                'х' -> "h"
                'ц' -> "c"
                'ч' -> "ch"
                'ш' -> "sh"
                'щ' -> "sh'"
                'ъ' -> ""
                'ы' -> "i"
                'ь' -> ""
                'э' -> "e"
                'ю' -> "yu"
                'я' -> "ya"
                'А' -> "A"
                'Б' -> "B"
                'В' -> "V"
                'Г' -> "G"
                'Д' -> "D"
                'Е' -> "E"
                'Ё' -> "E"
                'Ж' -> "Zh"
                'З' -> "Z"
                'И' -> "I"
                'Й' -> "I"
                'К' -> "K"
                'Л' -> "L"
                'М' -> "M"
                'Н' -> "N"
                'О' -> "O"
                'П' -> "P"
                'Р' -> "R"
                'С' -> "S"
                'Т' -> "T"
                'У' -> "U"
                'Ф' -> "F"
                'Х' -> "H"
                'Ц' -> "C"
                'Ч' -> "Ch"
                'Ш' -> "Sh"
                'Щ' -> "Sh'"
                'Ъ' -> ""
                'Ы' -> "I"
                'Ь' -> ""
                'Э' -> "E"
                'Ю' -> "Yu"
                'Я' -> "Ya"
                else -> char.toString()
            }
        }
        return result
    }

    fun dp2px(resource: Resources, dp: Int): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resource.displayMetrics)
    }

    fun px2dp(resource: Resources, px: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, resource.displayMetrics)
    }
}