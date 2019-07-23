package ru.skillbranch.devintensive.ui.profile

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.extensions.hideKeyboard

// TODO 05
// SplashTheme
// Необходимо реализовать тему, отображаемую при загрузке приложения до момента создания Activity
// Реализуй SplashTheme в соответствии с макетами. Необходимо реализовать ее отображение при запуске приложения до
// момента создания Activity. Как только Activity будет создана, необходимо установить AppTheme

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }
}
