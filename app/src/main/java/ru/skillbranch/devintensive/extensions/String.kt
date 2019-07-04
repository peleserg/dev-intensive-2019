package ru.skillbranch.devintensive.extensions

fun String.truncate(value : Int = 16) : String {
    if (this.trimEnd().length <= value) return this.trimEnd()
    return "${this.substring(0, value).trimEnd()}..."
}