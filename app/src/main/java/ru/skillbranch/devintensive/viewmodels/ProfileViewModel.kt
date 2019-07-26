package ru.skillbranch.devintensive.viewmodels

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository

// TODO 08
// **Преобразование Инициалов в Drawable
// Необходимо реализовать программное преобразование инициалов пользователя в Drawable с цветным фоном и буквами
// Реализуй программное преобразование инициалов пользователя (если доступны - заполнено хотя бы одно поле) в Drawable
// с фоном colorAccent (c учетом темы) и буквами инициалов (colorWhite) и установи полученное изображение как
// изображение по умолчанию для профиля пользователя

class ProfileViewModel : ViewModel() {
    private val repository : PreferencesRepository = PreferencesRepository
    private val profileData = MutableLiveData<Profile>()
    private val appTheme = MutableLiveData<Int>()

    init {
        profileData.value = repository.getProfile()
        appTheme.value = repository.getAppTheme()
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun getProfileData() : LiveData<Profile> = profileData

    fun getTheme() : LiveData<Int> = appTheme

    fun saveProfileData(profile: Profile) {
        repository.saveProfile(profile)
        profileData.value = profile
    }

    fun switchTheme() {
        if (appTheme.value == AppCompatDelegate.MODE_NIGHT_YES) {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_NO
        } else {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_YES
        }
        repository.saveNightTheme(appTheme.value!!)
    }

    fun repoIsValid(repo : String): Boolean {
        if ("github.com/" !in repo) {
            return false
        }

        val prefix = repo.substringBefore("github.com/")
        val suffix = repo.substringAfter("github.com/")

        val validPrefixes = setOf("", "https://", "www.", "https://www.")
        if (!validPrefixes.contains(prefix)) {
            return false
        }

        val invalidSuffices = setOf("", "enterprise", "features", "topics", "collections", "trending", "events",
            "marketplace", "pricing", "nonprofit", "customer-stories", "security", "login", "join")
        if (invalidSuffices.contains(suffix)) {
            return false
        }

        if ("/" in suffix) {
            return false
        }

        return true
    }
}