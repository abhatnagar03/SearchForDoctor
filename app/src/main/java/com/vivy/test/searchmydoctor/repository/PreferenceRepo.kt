package com.vivy.test.searchmydoctor.repository

interface PreferenceRepo {
    val PREF_ACCESS_TOKEN: String
    val PREF_TOKEN_FILE: String
    val PREF_REFRESH_TOKEN: String

    fun writePreference(fileName: String, key: String, value: String)
    fun readPreference(fileName: String, key: String, defaultValue: String): String
    fun clearAll(fileName: String)
}