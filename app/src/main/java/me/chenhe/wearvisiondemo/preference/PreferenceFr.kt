package me.chenhe.wearvisiondemo.preference

import android.os.Bundle
import me.chenhe.wearvision.preference.PreferenceFragmentCompat
import me.chenhe.wearvisiondemo.R

class PreferenceFr : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)
    }
}