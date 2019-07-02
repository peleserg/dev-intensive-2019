package ru.skillbranch.devintensive.utils

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
        return if (firstInitial == null && secondInitial == null) null else (firstInitial ?: "") + (secondInitial ?: "")
    }
}