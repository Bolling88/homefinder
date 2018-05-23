package com.xevenition.util

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

import java.util.ArrayList
import java.util.HashSet

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveUtil @Inject
constructor(app: Application) {
    private var prefsEditor: Editor? = null

    init {
        appSharedPrefs = app.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE)
    }

    fun getString(key: String): String {
        return appSharedPrefs.getString(key, "")
    }

    fun getLong(key: String): Long {
        return 0
    }

    fun saveString(key: String, content: String) {
        prefsEditor = appSharedPrefs.edit()
        prefsEditor!!.putString(key, content)
        prefsEditor!!.apply()
    }

    fun getBoolean(key: String): Boolean {
        return appSharedPrefs.getBoolean(key, false)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return appSharedPrefs.getBoolean(key, defaultValue)
    }

    fun saveBoolean(key: String, content: Boolean) {
        prefsEditor = appSharedPrefs.edit()
        prefsEditor!!.putBoolean(key, content)
        prefsEditor!!.apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return appSharedPrefs.getInt(key, defaultValue)
    }

    fun getDouble(key: String): Double {
        return 0.0
    }

    fun saveInt(key: String, content: Int) {
        prefsEditor = appSharedPrefs.edit()
        prefsEditor!!.putInt(key, content)
        prefsEditor!!.apply()
    }

    fun saveDouble(key: String, value: Double) {

    }

    fun saveLong(key: String, content: Long) {
        prefsEditor = appSharedPrefs.edit()
        prefsEditor!!.putLong(key, content)
        prefsEditor!!.apply()
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return appSharedPrefs.getLong(key, defaultValue)
    }

    fun saveList(key: String, list: ArrayList<String>) {
        val set = HashSet<String>()
        set.addAll(list)
        prefsEditor = appSharedPrefs.edit()
        prefsEditor!!.putStringSet(key, set)
        prefsEditor!!.apply()
    }

    fun getList(key: String): ArrayList<String>? {
        val set = appSharedPrefs.getStringSet(key, null) ?: return null
        return ArrayList(set)
    }

    companion object {
        private val APP_SHARED_PREFS = "com.xevenition.util.homefinder"

        private var appSharedPrefs: SharedPreferences
    }
}
