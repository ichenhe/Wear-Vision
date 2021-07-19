package me.chenhe.wearvisiondemo.preference

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.chenhe.wearvisiondemo.R

class PreferenceAty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aty_preference)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, PreferenceFr())
            .commit()
    }
}