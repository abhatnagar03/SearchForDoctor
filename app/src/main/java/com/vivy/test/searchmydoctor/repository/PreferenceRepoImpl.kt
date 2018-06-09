package com.vivy.test.searchmydoctor.repository

import android.content.Context
import android.content.SharedPreferences

class PreferenceRepoImpl(private var context: Context) : PreferenceRepo {
    override val PREF_ACCESS_TOKEN: String
        get() = "pref_access_token"
    override val PREF_TOKEN_FILE: String
        get() = "pref_token_file"
    override val PREF_REFRESH_TOKEN: String
        get() = "pref_refresh_token"

    override fun writePreference(fileName: String, key: String, value: String) {
        getSharedPreferences(fileName).edit().putString(key, value).apply()
    }

    override fun readPreference(fileName: String, key: String, defaultValue: String): String {
        return getSharedPreferences(fileName).getString(key, defaultValue)
    }

    override fun clearAll(fileName: String) {
        getSharedPreferences(fileName).edit().clear().apply()

    }

    private fun getSharedPreferences(fileName: String): SharedPreferences {
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }
}