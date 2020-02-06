package com.sujeet.githubissues.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class PreferenceHelper @Inject constructor(context: Context) {

    companion object {
        private const val PREF_BUFFER_PACKAGE_NAME = "com.sujeet.githubissues.utils.preferenceHelper"
        private const val PREF_LAST_SYNC = "syncTime"
    }

    private val bufferPref: SharedPreferences

    init {
        bufferPref = context.getSharedPreferences(PREF_BUFFER_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Store and retrieve the last time data was cached
     */
    var lastSyncTime: Long
        get() = bufferPref.getLong(PREF_LAST_SYNC, 0L)
        set(lastSyncTime) = bufferPref.edit().putLong(PREF_LAST_SYNC, lastSyncTime).apply()

    fun deleteAll() {
        bufferPref.clearAll()
    }

    private fun SharedPreferences.clearAll() {
        this.edit().clear().apply()
    }
}