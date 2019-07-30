package ru.skillbranch.devintensive.viewmodels

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository

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

        val invalidSuffices = setOf("enterprise", "features", "topics", "collections", "trending", "events",
            "marketplace", "pricing", "nonprofit", "customer-stories", "security", "login", "join")
        for (invalidSuffix in invalidSuffices) {
            if (invalidSuffix in suffix.toLowerCase()) {
                return false
            }
        }

        if ("/" in suffix) {
            return false
        }

        return true
    }
}