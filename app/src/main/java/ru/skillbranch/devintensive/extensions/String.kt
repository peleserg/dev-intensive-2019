package ru.skillbranch.devintensive.extensions

fun String.truncate(value : Int = 16) : String {
    if (this.trimEnd().length <= value) return this.trimEnd()
    return "${this.substring(0, value).trimEnd()}..."
}

// Реализуй extension позволяющий очистить строку от html тегов и html escape последовательностей ("& < > ' ""),
// а так же удалить пустые символы (пробелы) между словами если их больше 1. Необходимо вернуть модифицированную строку
// Пример:
// "<p class="title">Образовательное IT-сообщество Skill Branch</p>".stripHtml() //Образовательное IT-сообщество Skill Branch
// "<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml() //Образовательное IT-сообщество Skill Branch

fun String.stripHtml() : String {
    var insideTag = false
    var insideSpace = false
    var result = ""
    this.forEach { char ->
        when (char) {
            '<', '$' -> { insideTag = true; insideSpace = false }
            '>', ';' -> { insideTag = false; insideSpace = false }
            ' ' -> if (!insideTag) {
                if (!insideSpace) result += " "
                insideSpace = true
            }
            else -> {
                if (!insideTag) result += char.toString()
                insideSpace = false
            }
        }
    }
    return result
}