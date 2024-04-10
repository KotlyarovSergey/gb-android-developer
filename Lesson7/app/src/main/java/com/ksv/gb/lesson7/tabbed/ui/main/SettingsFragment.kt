package com.ksv.gb.lesson7.tabbed.ui.main

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.ksv.gb.lesson7.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}