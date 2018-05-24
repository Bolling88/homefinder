package com.xevenition.util

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import java.util.*
import javax.inject.Inject


class SaveUtil @Inject
constructor(app: Application) {

    companion object {
        val APP_SHARED_PREFS = "com.xevenition.util.homefinder"
        val KEY_GAME_ACTIVATED = "KEY_GAME_ACTIVATED"
        val KEY_GAME_NOT_PLAYING = "KEY_GAME_NOT_PLAYING"
        val KEY_MAP_MODE = "KEY_MAP_MODE"
        val KEY_MAP_MARKER_INFO = "KEY_MAP_MARKER_INFO"
        val KEY_FAVS = "KEY_FAVS"
        val KEY_INTRO_SEEN = "KEY_INTRO_SEEN"
        val KEY_SOLD_MODE = "KEY_SOLD_MODE"
        val KEY_FILTER_ON = "KEY_FILTER_ON"

        val PARAM_MIN_PRICE = "minPrice"
        val PARAM_MAX_PRICE = "maxPrice"
        val PARAM_MIN_SQUAREPRICE = "minSquarePrice"
        val PARAM_MAX_SQUAREPRICE = "maxSquarePrice"
        val PARAM_RENT = "maxRent"
        val PARAM_MIN_AREA = "minArea"
        val PARAM_MAX_AREA = "maxArea"
        val PARAM_MIN_PLOT = "minPlotArea"
        val PARAM_MAX_PLOT = "maxPlotArea"
        val PARAM_MIN_CONSTRUCTION = "minConstructionYear"

        val PARAM_MAX_CONSTRUCTION = "maxConstructionYear"
        val PARAM_DISTANCE_SEA = "maxDistanceToWater"
        val PARAM_ACTIVE_DAYS = "daysActive"

        val PARAM_SHOW_APARTMENT = "lägenhet"
        val PARAM_SHOW_VILLA = "villa"
        val PARAM_SHOW_TERRACED = "parhus"
        val PARAM_SHOW_HOLLIDAY = "fritidshus"
        val PARAM_SHOW_PLOT = "tomt/mark"
        val PARAM_SHOW_FARM = "gård"
        val PARAM_SHOW_ROW = "radhus"
        val PARAM_SHOW_SEMI = "kedjehus"

        val PARAM_SHOW_ROOM_1 = "1"
        val PARAM_SHOW_ROOM_2 = "2"
        val PARAM_SHOW_ROOM_3 = "3"
        val PARAM_SHOW_ROOM_4 = "4"
        val PARAM_SHOW_ROOM_5 = "5"
    }

    private var appSharedPrefs: SharedPreferences = app.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE)
    private var prefsEditor: Editor? = null

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

    fun getInt(key: String): Int {
        return appSharedPrefs.getInt(key, -1)
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

    fun isFilterActive(): Boolean {
        val minPrice = getInt(PARAM_MIN_PRICE)
        val maxPrice = getInt(PARAM_MAX_PRICE)
        val minArea = getInt(PARAM_MIN_AREA)
        val maxArea = getInt(PARAM_MAX_AREA)
        val minSqr = getInt(PARAM_MIN_SQUAREPRICE)
        val maxSqr = getInt(PARAM_MAX_SQUAREPRICE)
        val minPlot = getInt(PARAM_MIN_PLOT)
        val maxPlot = getInt(PARAM_MAX_PLOT)
        val minConstruct = getInt(PARAM_MIN_CONSTRUCTION)
        val maxConstruct = getInt(PARAM_MAX_CONSTRUCTION)
        val rent = getInt(PARAM_RENT)
        val distanceSea = getInt(PARAM_DISTANCE_SEA)
        val daysActive = getInt(PARAM_ACTIVE_DAYS)

        val showApartments = getBoolean(PARAM_SHOW_APARTMENT, true)
        val showVillas = getBoolean(PARAM_SHOW_VILLA, true)
        val showDetatched = getBoolean(PARAM_SHOW_TERRACED, true)
        val showCottage = getBoolean(PARAM_SHOW_HOLLIDAY, true)
        val showPlot = getBoolean(PARAM_SHOW_PLOT, true)
        val showFarms = getBoolean(PARAM_SHOW_FARM, true)
        val showSemi = getBoolean(PARAM_SHOW_SEMI, true)
        val showRow = getBoolean(PARAM_SHOW_ROW, true)

        val rooms1 = getBoolean(PARAM_SHOW_ROOM_1, true)
        val rooms2 = getBoolean(PARAM_SHOW_ROOM_2, true)
        val rooms3 = getBoolean(PARAM_SHOW_ROOM_3, true)
        val rooms4 = getBoolean(PARAM_SHOW_ROOM_4, true)
        val rooms5 = getBoolean(PARAM_SHOW_ROOM_5, true)
        if (showApartments && showVillas && showDetatched && showCottage && showPlot && showFarms && showSemi && showRow && rooms1 && rooms2 && rooms3 && rooms4 && rooms5) {
            if (minPrice == -1 && maxPrice == -1 && minArea == -1 && maxArea == -1 && minSqr == -1 && maxSqr == -1 && minPlot == -1 && maxPlot == -1 && minConstruct == -1 && maxConstruct == -1 && rent == -1 && distanceSea == -1 && daysActive == -1) {
                //filter is not active, do nothing
                return false
            } else {
                //filter is active
                return true
            }
        } else {
            //filter is active
            return true
        }
    }
}
