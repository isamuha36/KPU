package com.isamuha.kpu

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {

    private val PREFS_FILENAME = "com.isamuha.kpu.prefs"
    private val KEY_IS_LOGGED_IN = "is_logged_in"
    private val KEY_USERNAME = "username"
    private val KEY_PASSWORD = "password"

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    init {
        // Menyimpan satu user default untuk login (username: admin, password: admin123)
        if (!sharedPreferences.contains(KEY_USERNAME)) {
            editor.putString(KEY_USERNAME, "admin")
            editor.putString(KEY_PASSWORD, "admin123")
            editor.putBoolean(KEY_IS_LOGGED_IN, false)
            editor.apply()
        }
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun login(username: String, password: String): Boolean {
        val storedUsername = sharedPreferences.getString(KEY_USERNAME, null)
        val storedPassword = sharedPreferences.getString(KEY_PASSWORD, null)

        if (username == storedUsername && password == storedPassword) {
            editor.putBoolean(KEY_IS_LOGGED_IN, true)
            editor.apply()
            return true
        }
        return false
    }

    fun logout() {
        editor.putBoolean(KEY_IS_LOGGED_IN, false)
        editor.apply()
    }
}
