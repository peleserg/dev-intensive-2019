package ru.skillbranch.devintensive.repositories

import android.content.SharedPreferences
import android.preference.PreferenceManager
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.models.Profile

// TODO 02
// Редактирование профиля
// Реализуй бизнес логику режима редактирования профиля пользователя и сохранение измененных данных в SharedPreferences,
// режим редактирования должен сохраняться при перевороте экрана
// Необходимо реализовать сохранение введенных данных пользователя (данные сохраняются при нажатии пользователем кнопки
// сохранения данных (в EDIT_MODE @id/btn_edit)) с применением ViewModel и PreferencesRepository. Введенные данные
// должны быть сохранены в SharedPreferences. Режим редактирования должен сохраняться при перевороте экрана

object PreferencesRepository {

    private const val FIRST_NAME = "FIRST_NAME"
    private const val LAST_NAME = "LAST_NAME"
    private const val ABOUT = "ABOUT"
    private const val REPOSITORY = "REPOSITORY"
    private const val RATING = "RATING"
    private const val RESPECT = "RESPECT"

    private val prefs : SharedPreferences by lazy {
        val ctx = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun getProfile(): Profile = Profile(
        prefs.getString(FIRST_NAME, "")!!,
        prefs.getString(LAST_NAME, "")!!,
        prefs.getString(ABOUT, "")!!,
        prefs.getString(REPOSITORY, "")!!,
        prefs.getInt(RATING, 0),
        prefs.getInt(RESPECT, 0)
    )

    fun saveProfile(profile: Profile) {
        with(profile) {
            putValue(FIRST_NAME to firstName)
            putValue(LAST_NAME to lastName)
            putValue(ABOUT to about)
            putValue(REPOSITORY to repository)
            putValue(RATING to rating)
            putValue(RESPECT to respect)
        }
    }

    private fun putValue(pair : Pair<String, Any>) = with(prefs.edit()) {
        val key = pair.first
        val value = pair.second

        when(value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("Only primitive types can be stored in Shared Preferences")
        }

        apply()
    }
}