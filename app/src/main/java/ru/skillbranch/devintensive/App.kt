package ru.skillbranch.devintensive

import android.app.Application
import android.content.Context

// TODO 03
// Переключение режима Day/Night
// Необходимо реализовать логику переключения между режимами Day/Night и сохранение активного режима в SharedPreferences
// Реализуй переключение между режимами Day/Night при клике на кнопку @id/btn_switch_theme и установи дефолтное
// значение режима из PreferencesRepository (сохраненное в SharedPreferences) в методе onCreate() класса App.
// Атрибуты тем приложения colorAccentedSurface, сolorIcon, colorDivider

class App : Application() {
    companion object {
        private var instance : App? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        // TODO call me when application created
    }
}