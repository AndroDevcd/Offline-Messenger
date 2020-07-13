package com.intuisoft.offlinemessenger.presentation.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit


class AppPreferences(
    context: Context
) {

    companion object {
        private val NAME = "SanofiMDC"
        private val MODE = Context.MODE_PRIVATE
        val PREF_KEY_PLAY_NOTIFICATION_SOUND = Pair("PREF_KEY_PLAY_NOTIFICATION_SOUND", true)
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(NAME, MODE)

    // preferences keys along with default values
    private val PREF_KEY_APP_SUPPORTED = Pair("PREF_KEY_BLUETOOTH_ADDRESS", true)
    private val PREF_KEY_FIRST_OPEN = Pair("PREF_KEY_FIRST_OPEN", true)

    fun clear() {
        preferences.edit(commit = true) {
            clear()
        }
    }

    var isAppSupported: Boolean
        get() = preferences.getBoolean(
            PREF_KEY_APP_SUPPORTED.first,
            PREF_KEY_APP_SUPPORTED.second
        )
        set(value) = preferences.edit {
            putBoolean(
                PREF_KEY_APP_SUPPORTED.first,
                value
            )
        }

    var isFirstOpen: Boolean
        get() = preferences.getBoolean(
            PREF_KEY_FIRST_OPEN.first,
            PREF_KEY_FIRST_OPEN.second
        )
        set(value) = preferences.edit {
            putBoolean(
                PREF_KEY_FIRST_OPEN.first,
                value
            )
        }

    fun getBoolean(key: String, defValue: Boolean = false) =
        preferences.getBoolean(
            key,
            defValue
        )

    fun setBoolean(key: String, defValue: Boolean) =
        preferences.edit {
            putBoolean(
                key,
                defValue
            )
        }

}