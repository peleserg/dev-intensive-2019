package ru.skillbranch.devintensive.extensions

fun String.truncate(value : Int = 16) : String {
    if (this.trimEnd().length <= value) return this.trimEnd()
    return "${this.substring(0, value).trimEnd()}..."
}

fun String.stripHtml() : String {
    var insideTag = false
    var multiSpace = false
    var result = ""
    this.forEach { char ->
        when (char) {
            '<', '$' -> insideTag = true
            '>', ';' -> insideTag = false
            else -> if (!insideTag) {
                if (!multiSpace || char != ' ') result += char.toString()
                multiSpace = char == ' '
            }
        }
    }
    return result
}