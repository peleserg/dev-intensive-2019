package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val view = if (currentFocus == null) View(this) else currentFocus
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.isKeyboardOpen() : Boolean {
    val rootView: ViewGroup = findViewById(android.R.id.content)
    val rect = Rect()
    rootView.getWindowVisibleDisplayFrame(rect)
    val heightDiff = rootView.rootView.height - (rect.bottom - rect.top)
    return heightDiff > rootView.rootView.height / 4
}

fun Activity.isKeyboardClosed() : Boolean = !isKeyboardOpen()